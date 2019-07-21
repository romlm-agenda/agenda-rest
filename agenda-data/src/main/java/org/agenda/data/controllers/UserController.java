/**
 * @since 18 juil. 2019
 */
package org.agenda.data.controllers;

import org.agenda.data.services.UserService;
import org.agenda.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LE MIERE Romain
 *
 */
@RestController
@RequestMapping("/users/")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	static {
		// TODO : add logger handlers here
	}

	@Autowired
	private UserService users;

	@PostMapping("/public/login")
	public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
		User user = null;
		try {
			user = users.loginUser(email, password);
		} catch (BadCredentialsException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);

	}

	@PostMapping("/public/register")
	public ResponseEntity<Void> register(@RequestBody User user) {
		try {
			user = users.createUser(user);
			return ResponseEntity.created(null).build();
		} catch (DuplicateKeyException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
	}

}
