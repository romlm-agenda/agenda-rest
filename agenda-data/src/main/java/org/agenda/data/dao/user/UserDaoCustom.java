/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import org.agenda.model.User;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author LE MIERE Romain
 *
 */
public interface UserDaoCustom {

	User loginUser(String email, String password) throws BadCredentialsException;

}
