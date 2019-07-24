/**
 * @since 21 juil. 2019
 */
package org.agenda.data.model.mappers;

import java.util.List;

import org.agenda.data.model.beans.DayBean;
import org.agenda.data.model.beans.UserBean;
import org.agenda.model.User;

/**
 * @author LE MIERE Romain
 *
 */
public class UserMapper {

	private UserMapper() {
	}

	public static User mapUserBeanToUser(UserBean pUser) {
		User user = new User();
		user.setId(pUser.getId());
		user.setFirstName(pUser.getFirstName());
		user.setLastName(pUser.getLastName());
		user.setEmail(pUser.getEmail());
		user.setPassword(pUser.getPassword());
		user.setBirthDate(pUser.getBirthDate());

		return user;

	}

	public static UserBean mapUserToUserBean(User pUser) {
		UserBean user = new UserBean();
		user.setId(pUser.getId());
		user.setFirstName(pUser.getFirstName());
		user.setLastName(pUser.getLastName());
		user.setEmail(pUser.getEmail());
		user.setPassword(pUser.getPassword());
		user.setBirthDate(pUser.getBirthDate());
		return user;

	}

	public static UserBean mapUserToUserBean(User pUser, List<DayBean> pDays) {
		UserBean user = mapUserToUserBean(pUser);
		user.setDays(pDays);
		return user;
	}

}
