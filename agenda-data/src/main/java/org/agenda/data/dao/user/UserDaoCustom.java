/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.exceptions.UserNotFoundException;
import org.agenda.model.Day;
import org.agenda.model.User;

/**
 * @author LE MIERE Romain
 *
 */
public interface UserDaoCustom {

	Optional<User> loginUser(
	    String email,
	    String password
	);

	Optional<User> getInfos(String id);

	User updateUser(User user) throws UserNotFoundException;

	Day saveDay(
	    String userId,
	    Day day
	) throws UserNotFoundException;

	Optional<Day> getDay(
	    String userId,
	    LocalDate date
	);

	List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	Long deleteDay(
	    String userId,
	    LocalDate date
	);

	Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

}
