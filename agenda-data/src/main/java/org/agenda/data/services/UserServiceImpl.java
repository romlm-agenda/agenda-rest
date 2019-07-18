/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services;

import org.agenda.data.dao.user.UserDao;
import org.agenda.model.User;
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
	public User createUser(User user) throws DuplicateKeyException {
		// TODO Implement the method
		return null;
	}

}
