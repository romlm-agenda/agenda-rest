/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.beans.data.DayBean;
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

	DayBean saveDay(
	    String userId,
	    DayBean day
	);

	Optional<DayBean> getDay(
	    String userId,
	    LocalDate date
	);

	List<DayBean> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	);

}
