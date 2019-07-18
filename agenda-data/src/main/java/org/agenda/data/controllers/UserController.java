/**
 * @since 18 juil. 2019
 */
package org.agenda.data.controllers;

import org.agenda.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping
	public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
		return 

	}

}
