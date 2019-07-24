/**
 * @since 16 juil. 2019
 */
package org.agenda.security.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.agenda.security.utils.UniqueIdGenerator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LE MIERE Romain
 *
 */
@RestController
@RequestMapping("/token/")
public class TokenController {

	private List<String> tokens = new ArrayList<>();

	@GetMapping
	public String getToken() {
		String token = UniqueIdGenerator.generateRandomId(20, 20, Optional.empty());
		tokens.add(token);
		return token;
	}

	@PostMapping
	public boolean isTokenValid(@RequestBody String token) {
		return tokens.contains(token);
	}

	@DeleteMapping
	public void deleteToken(@RequestBody String token) {
		tokens.remove(token);
	}

}
