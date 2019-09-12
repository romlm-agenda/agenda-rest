/**
 * @since 16 juil. 2019
 */
package org.agenda.security.controllers;

import org.agenda.security.services.accessTokens.AccessTokensService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private AccessTokensService tokens;

	@GetMapping
	public String getToken()
	{
		return tokens.getToken();
	}

	@PostMapping
	public boolean isTokenValid(@RequestBody String token)
	{
		return tokens.checkToken(token);
	}

	@DeleteMapping
	public void deleteToken(@RequestBody String token)
	{
		tokens.deleteToken(token);
	}

}
