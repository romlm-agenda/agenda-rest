/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.exceptions.BadCredentialsException;
import org.agenda.model.Day;
import org.agenda.model.Month;
import org.agenda.model.MonthBasedYear;
import org.agenda.model.User;
import org.agenda.model.Week;
import org.agenda.model.WeekBasedYear;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author LE MIERE Romain
 *
 */
@Service
public interface UserService {

	User createUser(User user) throws DuplicateKeyException;

	User loginUser(
	    String email,
	    String password
	) throws BadCredentialsException;

	User getInfos(String id) throws NullPointerException;

	void deleteUser(String id) throws NullPointerException;

	User updateUser(User user) throws NullPointerException;

	Optional<Day> saveDay(
	    String userId,
	    Day day
	);

	Optional<Day> getDay(
	    String userId,
	    LocalDate date
	);

	boolean deleteDay(
	    String userId,
	    LocalDate date
	);

	List<Day> saveDays(
	    String userId,
	    List<Day> days
	);

	List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	Week getWeek(
	    String userId,
	    LocalDate date
	);

	List<Week> getWeeks(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	Month getMonth(
	    String userId,
	    LocalDate date
	);

	List<Month> getMonths(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	MonthBasedYear getMonthBasedYear(
	    String userId,
	    LocalDate date
	);

	WeekBasedYear getWeekBasedYear(
	    String userId,
	    LocalDate date
	);

	List<MonthBasedYear> getMonthBasedYears(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	List<WeekBasedYear> getWeekBasedYears(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

}
