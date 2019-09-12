/**
 * @since 24 juil. 2019
 */
package org.agenda.security.controllers;

import org.agenda.security.services.userIds.UserIdsService;
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
	private UserIdsService userIds;

	@GetMapping
	public String getInstance(@RequestParam("userId") String userId)
	{
		return userIds.getTokenForUser(userId);
	}

	@PostMapping
	public boolean isUserValid(
	    @RequestParam("userId") String userId,
	    @RequestParam("userAuthKey") String authKey
	)
	{
		return userIds.isUserValid(userId, authKey);
	}

	@DeleteMapping
	public void deleteUser(@RequestParam("userId") String userId)
	{
		userIds.deleteUser(userId);
	}

}
