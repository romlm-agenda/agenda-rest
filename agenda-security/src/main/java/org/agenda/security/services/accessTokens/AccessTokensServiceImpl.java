/**
 * @since 12 sept. 2019
 */
package org.agenda.security.services.accessTokens;

import org.agenda.security.beans.AccessTokenBean;
import org.agenda.security.dao.accessTokens.AccessTokensDao;
import org.agenda.security.utils.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class AccessTokensServiceImpl implements AccessTokensService {

	@Autowired
	private AccessTokensDao tokens;

	@Override
	public String getToken()
	{
		AccessTokenBean token = new AccessTokenBean(UniqueIdGenerator.generateRandomId(20, 20));
		tokens.save(token);
		return token.getAccessToken();
	}

	@Override
	public boolean checkToken(String token)
	{
		return tokens.existsAccessTokenBeanByAccessToken(token);
	}

	@Override
	public void deleteToken(String token)
	{
		tokens.deleteByAccessToken(token);

	}

}
