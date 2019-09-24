/**
 * @since 24 juil. 2019
 */
package org.agenda.security.controllers;

import org.agenda.security.services.userIds.UserIdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger logger = LoggerFactory.getLogger(UserIdentifiersController.class);

	@Autowired
	private UserIdsService userIds;

	@GetMapping
	public String getInstance(@RequestParam("userId") String userId)
	{
		logger.info("user token requested for userId: "
		        + userId);
		return userIds.getTokenForUser(userId);
	}

	@PostMapping
	public boolean isUserValid(
	    @RequestParam("userId") String userId,
	    @RequestParam("userAuthKey") String authKey
	)
	{
		boolean valid = userIds.isUserValid(userId, authKey);
		if (valid)
			logger.info("valid user requested for userId: "
			        + userId);
		else
			logger.warn("wrong userId or token given for userId: "
			        + userId
			        + " and token: "
			        + authKey);
		return valid;
	}

	@DeleteMapping
	public void deleteUser(@RequestParam("userId") String userId)
	{
		userIds.deleteUser(userId);
		logger.info("deleted user auth for userId: "
		        + userId);
	}

}
