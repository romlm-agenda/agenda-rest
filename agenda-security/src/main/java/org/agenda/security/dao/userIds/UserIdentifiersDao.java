/**
 * @since 24 juil. 2019
 */
package org.agenda.security.dao.userIds;

import org.agenda.security.beans.UserIdentifierBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LE MIERE Romain
 *
 */
@Repository
public interface UserIdentifiersDao extends JpaRepository<UserIdentifierBean, String> {

	boolean existsUserIdentifierBeanByUserIdAndAuthKey(String userId, String token);

}
