/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.exceptions.BadCredentialsException;
import org.agenda.model.Day;
import org.agenda.model.User;
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

	Day saveDay(
	    String userId,
	    Day day
	) throws NullPointerException;

	Optional<Day> getDay(
	    String userId,
	    LocalDate date
	);

	List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

	boolean deleteDay(
	    String userId,
	    LocalDate date
	);

	Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

}
