/**
 * @since 28 juil. 2019
 */
package org.agenda.security.services.userIds;

import java.util.Optional;

import org.agenda.security.beans.UserIdentifierBean;
import org.agenda.security.dao.userIds.UserIdentifiersDao;
import org.agenda.security.utils.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class UserIdsServiceImpl implements UserIdsService {

	@Autowired
	private UserIdentifiersDao userIds;

	@Override
	public String registerUser(String userId)
	{
		Optional<UserIdentifierBean> userIdentifier = userIds.findById(userId);
		if (userIdentifier.isPresent())
			return userIdentifier.get().getAuthKey();
		String token = UniqueIdGenerator.generateRandomId(10, 10, "md5");
		userIds.save(new UserIdentifierBean(userId, token));
		return token;
	}

	@Override
	public boolean isUserValid(
	    String userId,
	    String authKey
	)
	{
		return userIds.findByUserIdAndAuthKey(userId, authKey).isPresent();
	}

	@Override
	public void deleteUser(String userId)
	{
		userIds.deleteById(userId);

	}

}
