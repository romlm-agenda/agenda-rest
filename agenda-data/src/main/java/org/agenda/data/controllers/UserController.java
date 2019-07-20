/**
 * @since 18 juil. 2019
 */
package org.agenda.data.controllers;

import org.agenda.data.services.UserService;
import org.agenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private UserService users;

	@GetMapping("/public/login")
	public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
		User user = null;
		try {
			user = users.loginUser(email, password);
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(user);

	}

}
