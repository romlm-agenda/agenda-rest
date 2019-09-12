/**
 * @since 12 sept. 2019
 */
package org.agenda.security.dao.accessTokens;

import org.agenda.security.beans.AccessTokenBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LE MIERE Romain
 *
 */
public interface AccessTokensDao extends JpaRepository<AccessTokenBean, Long> {

	boolean existsAccessTokenBeanByAccessToken(String accessToken);

	void deleteByAccessToken(String accessToken);

}
