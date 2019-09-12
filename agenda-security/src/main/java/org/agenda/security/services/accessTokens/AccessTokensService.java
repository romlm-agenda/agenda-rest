/**
 * @since 12 Sept. 2019
 * @author LE MIERE Romain
 *
 */
package org.agenda.security.services.accessTokens;

import org.springframework.stereotype.Service;

@Service
public interface AccessTokensService {
	
	String getToken();
	
	boolean checkToken(String token);
	
	void deleteToken(String token);

}