/**
 * @since 21 juil. 2019
 */
package org.agenda.data.model.mappers;

import java.util.List;

import org.agenda.data.model.beans.data.UserBean;
import org.agenda.model.Day;
import org.agenda.model.User;

/**
 * @author LE MIERE Romain
 *
 */
public class UserMapper {

	private UserMapper() {
	}

	public static User mapUserBeanToUser(UserBean user)
	{
		User vUser = new User();
		vUser.setId(user.getId());
		vUser.setFirstName(user.getFirstName());
		vUser.setLastName(user.getLastName());
		vUser.setEmail(user.getEmail());
		vUser.setPassword(user.getPassword());
		vUser.setBirthDate(user.getBirthDate());

		return vUser;

	}

	public static UserBean mapUserToUserBean(User user)
	{
		UserBean vUser = new UserBean();
		vUser.setId(user.getId());
		vUser.setFirstName(user.getFirstName());
		vUser.setLastName(user.getLastName());
		vUser.setEmail(user.getEmail());
		vUser.setPassword(user.getPassword());
		vUser.setBirthDate(user.getBirthDate());
		return vUser;

	}

	public static UserBean mapUserToUserBean(
	    User user,
	    List<Day> days
	)
	{
		UserBean vUser = mapUserToUserBean(user);
		vUser.setDays(days);
		return vUser;
	}

}
