/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services.user;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.agenda.data.dao.user.UserDao;
import org.agenda.data.model.beans.data.UserBean;
import org.agenda.data.model.exceptions.BadCredentialsException;
import org.agenda.data.model.exceptions.UserNotFoundException;
import org.agenda.data.model.mappers.UserMapper;
import org.agenda.model.Day;
import org.agenda.model.Month;
import org.agenda.model.User;
import org.agenda.model.Week;
import org.agenda.model.Year;
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
	public Week saveWeek(
	    String userId,
	    Week week
	)
	{
		List<Day> days = week.getDays();
		Week results = new Week(week.getYearId(), week.getWeekId(), this.saveDays(userId, days));
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
		// @formatter:off
		for (int i = -currentDay + 1; i <= 7 - currentDay; i++) {
		// @formatter:on
			LocalDate currentDate = date.plusDays(Integer.valueOf(i).longValue());
			Optional<Day> optDay = this.getDay(userId, currentDate);
			days.add(optDay.isPresent() ? optDay.get() : new Day(currentDate, Arrays.asList(), Arrays.asList()));
		}
		final int weekId = date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
		Week week = new Week(date.getYear(), weekId, days);
		return week;
	}

	@Override
	public boolean deleteWeek(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return false;
	}

	@Override
	public List<Week> saveWeeks(
	    String userId,
	    List<Week> weeks
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public List<Week> getWeeks(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Long deleteWeeks(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Month getMonth(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public List<Month> getMonths(
	    String userId,
	    LocalDate fro,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Year getYear(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Year getYears(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

}
