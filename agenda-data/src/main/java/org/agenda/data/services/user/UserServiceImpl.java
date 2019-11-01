/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services.user;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.agenda.data.dao.user.UserDao;
import org.agenda.data.model.beans.data.UserBean;
import org.agenda.data.model.exceptions.BadCredentialsException;
import org.agenda.data.model.exceptions.UserNotFoundException;
import org.agenda.data.model.mappers.UserMapper;
import org.agenda.model.Day;
import org.agenda.model.Month;
import org.agenda.model.MonthBasedYear;
import org.agenda.model.User;
import org.agenda.model.Week;
import org.agenda.model.WeekBasedYear;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao users;

	@Override
	public User createUser(User user) throws DuplicateKeyException
	{
		user.setId(null);
		UserBean userToAdd = UserMapper.mapUserToUserBean(user);
		if ((userToAdd = users.save(userToAdd)) == null) {
			throw new DuplicateKeyException("duplicate key email");
		}
		return UserMapper.mapUserBeanToUser(userToAdd);
	}

	@Override
	public User loginUser(
	    String email,
	    String password
	) throws BadCredentialsException
	{
		Optional<User> user = users.loginUser(email, password);
		if (user.isEmpty())
			throw new BadCredentialsException("bad credentials given, user not found");
		return user.get();
	}

	@Override
	public User getInfos(String id) throws NullPointerException
	{
		Optional<User> user = users.getInfos(id);
		if (user.isEmpty())
			throw new NullPointerException(String.format("user not found for id %s", id));
		return user.get();
	}

	@Override
	public void deleteUser(String id) throws NullPointerException
	{
		users.deleteById(new ObjectId(id));

	}

	@Override
	public User updateUser(User user) throws NullPointerException
	{
		User oldUser = new User();
		try {
			oldUser = users.updateUser(user);
		} catch (UserNotFoundException e) {
			throw new NullPointerException(e.getMessage());
		}
		return oldUser;
	}

	@Override
	public Day saveDay(
	    String userId,
	    Day day
	) throws NullPointerException
	{
		return users.saveDay(userId, day);
	}

	@Override
	public Optional<Day> getDay(
	    String userId,
	    LocalDate date
	)
	{
		return users.getDay(userId, date);
	}

	@Override
	public List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		return users.getDays(userId, from, to);
	}

	@Override
	public boolean deleteDay(
	    String userId,
	    LocalDate date
	)
	{
		return users.deleteDay(userId, date) == 1;
	}

	@Override
	public Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		return users.deleteDays(userId, from, to);
	}

	@Override
	public List<Day> saveDays(
	    String userId,
	    List<Day> days
	)
	{
		List<Day> results = new ArrayList<>();
		for (Day day : days) {
			results.add(this.saveDay(userId, day));
		}
		return results;
	}

	@Override
	public Week getWeek(
	    String userId,
	    LocalDate date
	)
	{
		final int currentDay = date.getDayOfWeek().getValue();
		List<Day> days = new ArrayList<>();
		for (int i = -currentDay + 1; i <= 7 - currentDay; i++) {
			LocalDate currentDate = date.plusDays(Integer.valueOf(i).longValue());
			Optional<Day> optDay = this.getDay(userId, currentDate);
			days.add(optDay.isPresent() ? optDay.get() : new Day(currentDate, Arrays.asList(), Arrays.asList()));
		}
		final int weekId = date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
		Week week = new Week(date.getYear(), weekId, days);
		return week;
	}

	@Override
	public List<Week> getWeeks(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		List<LocalDate> dates = new ArrayList<>();
		if (from.isAfter(to)) {
			LocalDate date = from;
			from = to;
			to = date;
		}
		for (LocalDate date = from; date.isBefore(to) || date.isEqual(to); date = date.plusWeeks(1))
			dates.add(date);

		List<Week> weeks = dates.stream().map(date -> this.getWeek(userId, date)).collect(Collectors.toList());

		return weeks;
	}

	@Override
	public Month getMonth(
	    String userId,
	    LocalDate date
	)
	{
		LocalDate lastDayOfMonth = date
		        .plusDays(YearMonth.of(date.getYear(), date.getMonth()).lengthOfMonth() - date.getDayOfMonth());
		LocalDate firstDayOfMonth = date.minusDays(date.getDayOfMonth() - 1);
		Month month = new Month();
		month.setYearId(date.getYear());
		month.setMonthId(date.getMonthValue());

		month.setDays(this.getDays(userId, firstDayOfMonth, lastDayOfMonth));

		return month;
	}

	@Override
	public List<Month> getMonths(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		List<Month> months = new ArrayList<>();
		for (LocalDate date = from.minusDays(from.getDayOfMonth() - 1); date.isBefore(to)
		        || date.isEqual(to); date = date.plusMonths(1)) {
			months.add(this.getMonth(userId, date));

		}
		return months;
	}

	@Override
	public MonthBasedYear getMonthBasedYear(
	    String userId,
	    LocalDate date
	)
	{
		MonthBasedYear year = new MonthBasedYear(date.getYear(), new ArrayList<>());
		for (LocalDate d = LocalDate.of(date.getYear(), 1, 1); d
		        .isBefore(LocalDate.of(date.getYear(), 12, 31)); d = d.plusMonths(1)) {
			year.getMonths().add(this.getMonth(userId, d));

		}
		return year;
	}

	@Override
	public WeekBasedYear getWeekBasedYear(
	    String userId,
	    LocalDate date
	)
	{
		WeekBasedYear year = new WeekBasedYear(date.getYear(), new ArrayList<>());
		for (LocalDate d = LocalDate.of(date.getYear(), 1, 1); d
		        .isBefore(LocalDate.of(date.getYear(), 12, 31)); d = d.plusWeeks(1)) {
			year.getWeeks().add(this.getWeek(userId, d));
		}
		return year;
	}

	@Override
	public List<MonthBasedYear> getMonthBasedYears(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		List<MonthBasedYear> years = new ArrayList<>();
		for (LocalDate date = LocalDate.of(from.getYear(), 1, 1); date.isBefore(to)
		        || date.isEqual(to); date = date.plusYears(1)) {
			years.add(this.getMonthBasedYear(userId, date));
		}
		return years;
	}

	@Override
	public List<WeekBasedYear> getWeekBasedYears(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

}
