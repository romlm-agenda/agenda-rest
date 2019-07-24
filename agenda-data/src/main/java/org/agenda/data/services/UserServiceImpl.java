/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services;

import org.agenda.data.beans.UserBean;
import org.agenda.data.dao.user.UserDao;
import org.agenda.data.model.mappers.UserMapper;
import org.agenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
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
	public User createUser(User user) throws DuplicateKeyException {
		user.setId(null);
		UserBean userToAdd = UserMapper.mapUserToUserBean(user);
		if ((userToAdd = users.save(userToAdd)) == null) {
			throw new DuplicateKeyException("duplicate key email");
		}
		return UserMapper.mapUserBeanToUser(userToAdd);
	}

	@Override
	public User loginUser(String email, String password) throws BadCredentialsException {
		return users.loginUser(email, password);
	}

}
