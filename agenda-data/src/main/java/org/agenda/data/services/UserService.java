/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services;

import org.agenda.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
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

}
