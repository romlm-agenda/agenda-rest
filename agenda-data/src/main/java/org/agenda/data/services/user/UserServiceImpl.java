/**
 * @since 18 juil. 2019
 */
package org.agenda.data.services.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.agenda.data.dao.user.UserDao;
import org.agenda.data.model.beans.data.UserBean;
import org.agenda.data.model.mappers.UserMapper;
import org.agenda.model.Day;
import org.agenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao users;

	@Override
	public User createUser(User user) throws DuplicateKeyException
	{
		user.setId(null);
		UserBean userToAdd = UserMapper.mapUserToUserBean(user);
		if ((userToAdd = users.save(userToAdd)) == null) {
			throw new DuplicateKeyException("duplicate key email");
		}
		return UserMapper.mapUserBeanToUser(userToAdd);
	}

	@Override
	public User loginUser(
	    String email,
	    String password
	) throws BadCredentialsException
	{
		Optional<User> user = users.loginUser(email, password);
		if (user.isEmpty())
			throw new BadCredentialsException("bad credentials given, user not found");
		return user.get();
	}

	@Override
	public User getInfos(String id) throws NullPointerException
	{
		Optional<User> user = users.getInfos(id);
		if (user.isEmpty())
			throw new NullPointerException(String.format("user not found for id %s", id));
		return user.get();
	}

	@Override
	public void deleteUser(String id) throws NullPointerException
	{
		// TODO Implement the method

	}

	@Override
	public User updateUser(User user) throws NullPointerException
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Day saveDay(
	    String userId,
	    Day day
	) throws NullPointerException
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Optional<Day> getDay(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Long deleteDay(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}

}
