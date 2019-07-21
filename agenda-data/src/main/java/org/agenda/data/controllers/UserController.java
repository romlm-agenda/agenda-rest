/**
 * @since 18 juil. 2019
 */
package org.agenda.data.controllers;

import java.time.LocalDate;
import java.util.List;

import org.agenda.data.services.UserService;
import org.agenda.model.Day;
import org.agenda.model.Month;
import org.agenda.model.User;
import org.agenda.model.Week;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private UserService users;

	// public enpoints

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

	// private endpoints

	@DeleteMapping("/private/delete")
	public ResponseEntity<Void> delete(@RequestBody User user) {
		return null;
	}

	@GetMapping("/private/day")
	public ResponseEntity<Day> getDayByDate(@RequestParam LocalDate date) {
		return null;
	}

	@GetMapping("/private/days")
	public ResponseEntity<List<Day>> getDaysBetweenDates(@RequestParam LocalDate from, @RequestParam LocalDate to) {
		return null;
	}

	@GetMapping("/private/week")
	public ResponseEntity<Week> getWeek(@RequestParam Integer yearId, @RequestParam Integer weekId) {
		return null;
	}

	@GetMapping("/private/weekByDate")
	public ResponseEntity<Week> getWeekByDate(@RequestParam LocalDate date) {
		return null;
	}

	@GetMapping("/private/weeks")
	public ResponseEntity<List<Week>> getWeeks(@RequestParam Integer fromYearId, @RequestParam Integer fromWeekId,
			@RequestParam Integer toYearId, @RequestParam Integer toWeekId) {
		return null;
	}

	@GetMapping("/private/weeksByDate")
	public ResponseEntity<List<Week>> getWeeksByDate(@RequestParam LocalDate from, @RequestParam LocalDate to) {
		return null;
	}

	@GetMapping("/private/month")
	public ResponseEntity<Month> getMonth(@RequestParam Integer yearId, @RequestParam Integer monthId) {
		return null;
	}

	@GetMapping("/private/monthByDate")
	public ResponseEntity<Month> getMonthByDate(@RequestParam LocalDate date) {
		return null;
	}

}
