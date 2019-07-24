/**
 * @since 24 juil. 2019
 */
package org.agenda.security.controllers;

import java.util.Optional;

import org.agenda.security.beans.UserIdentifierBean;
import org.agenda.security.dao.UserIdentifiersDao;
import org.agenda.security.utils.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LE MIERE Romain
 *
 */
@RestController
@RequestMapping("/user/")
public class UserIdentifiersController {

	@Autowired
	private UserIdentifiersDao userIds;

	@GetMapping
	public String getInstance(@RequestParam String userId) {

		String token = UniqueIdGenerator.generateRandomId(15, 15, Optional.of("md5"));
		UserIdentifierBean bean = new UserIdentifierBean(userId, token);
		userIds.save(bean);
		return bean.getAuthKey();

	}

	@PostMapping
	public boolean isUserValid(@RequestParam String userId, @RequestParam String token) {
		Optional<UserIdentifierBean> bean = userIds.findByUserIdAndAuthKey(userId, token);

		return bean.isPresent();
	}

	@DeleteMapping
	public void deleteUser(@RequestParam String userId) {
		userIds.deleteById(userId);
	}

}
