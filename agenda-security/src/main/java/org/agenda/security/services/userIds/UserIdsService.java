/**
 * @since 28 juil. 2019
 */
package org.agenda.security.services.userIds;

import org.springframework.stereotype.Service;

/**
 * @author LE MIERE Romains
 *
 */
@Service
public interface UserIdsService {

	String registerUser(String userId);

	boolean isUserValid(
	    String userId,
	    String authKey
	);

	void deleteUser(String userId);

}
