/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import java.util.Optional;

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

	Day saveDay(
	    String id,
	    Day day
	);

}
