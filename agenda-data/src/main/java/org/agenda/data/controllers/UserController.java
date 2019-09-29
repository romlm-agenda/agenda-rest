/**
 * @since 18 juil. 2019
 */
package org.agenda.data.controllers;

import java.time.LocalDate;
import java.util.List;

import org.agenda.data.model.exceptions.BadCredentialsException;
import org.agenda.data.proxies.SecurityUserIdProxy;
import org.agenda.data.services.user.UserService;
import org.agenda.model.Day;
import org.agenda.model.Month;
import org.agenda.model.User;
import org.agenda.model.Week;
import org.agenda.model.Year;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

	@Autowired
	private UserService users;
	@Autowired
	private SecurityUserIdProxy securityUser;

	// public endpoints

	@GetMapping("/public/login")
	public ResponseEntity<User> loginUser(
	    @RequestParam String email,
	    @RequestParam String password
	)
	{
		try {
			User user = users.loginUser(email, password);
			String userAuthKey = securityUser.getInstance(user.getId());
			return ResponseEntity.ok().header("userAuthKey", userAuthKey).body(user);
		} catch (BadCredentialsException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/public/register")
	public ResponseEntity<Void> register(@RequestBody User user)
	{
		try {
			user = users.createUser(user);
			return ResponseEntity.created(null).build();
		} catch (DuplicateKeyException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
	}

	// private endpoints

	@DeleteMapping("/private/delete")
	public ResponseEntity<Void> delete(@RequestHeader("userId") String userId)
	{
		try {

			users.deleteUser(userId);
		} catch (NullPointerException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(204).build();
	}

	@GetMapping("/private/info")
	public ResponseEntity<User> getInfo(@RequestHeader("userId") String userId)
	{
		try {
			User user = users.getInfos(userId);
			return ResponseEntity.ok(user);
		} catch (NullPointerException e) {
			logger.error(String.format("user not found for id %s", userId), e);
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/private/update")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		try {
			User res = users.updateUser(user);
			return ResponseEntity.ok(res);

		} catch (NullPointerException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/private/day")
	public ResponseEntity<Day> getDayByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
	)
	{
		return null;
	}

	@GetMapping("/private/days")
	public ResponseEntity<List<Day>> getDaysBetweenDates(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
	)
	{
		return null;
	}

	@GetMapping("/private/week")
	public ResponseEntity<Week> getWeekByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
	)
	{
		return null;
	}

	@GetMapping("/private/weeks")
	public ResponseEntity<List<Week>> getWeeksByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
	)
	{
		return null;
	}

	@GetMapping("/private/month")
	public ResponseEntity<Month> getMonthByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
	)
	{
		return null;
	}

	@GetMapping("/private/months")
	public ResponseEntity<List<Month>> getMonthsByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
	)
	{
		return null;
	}

	@GetMapping("/private/year")
	public Year getYearByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
	)
	{
		return null;
	}

	@GetMapping("/private/years")
	public ResponseEntity<List<Year>> getYearsByDate(
	    @RequestHeader("userId") String userId,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
	    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
	)
	{
		return null;
	}

}
