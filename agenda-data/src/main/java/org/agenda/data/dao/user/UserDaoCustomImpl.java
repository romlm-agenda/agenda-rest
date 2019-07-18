/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import org.agenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class UserDaoCustomImpl implements UserDaoCustom {

	@Autowired
	private MongoTemplate mongo;

	@Override
	public User loginUser(String email, String password) throws BadCredentialsException {
		// TODO Implement the method
		return null;
	}

}
