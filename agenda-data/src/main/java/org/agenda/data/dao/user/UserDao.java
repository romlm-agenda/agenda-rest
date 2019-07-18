/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import org.agenda.data.beans.UserBean;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LE MIERE Romain
 *
 */
@Repository
public interface UserDao extends UserDaoCustom, MongoRepository<UserBean, ObjectId> {

}
