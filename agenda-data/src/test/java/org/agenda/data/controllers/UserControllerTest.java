/**
 * @since 27 sept. 2019
 */
package org.agenda.data.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Optional;

import org.agenda.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LE MIERE Romain
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private static final String USER_ID = "5da1d52ceff9812c30ecc7bc";

	@Autowired
	private UserController users;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#loginUser(java.lang.String, java.lang.String, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public final void testLoginUser()
	{
		ResponseEntity<User> response = users.loginUser("sophie@fon.fec", "1234");
		assertTrue(response.getStatusCodeValue() == 200);
		assertTrue(response.getHeaders().containsKey("userAuthKey"));
		User user = response.getBody();
		assertTrue(user.getEmail().equals("sophie@fon.fec"));

		response = users.loginUser("sophie@fon.fec", null);
		assertTrue(response.getStatusCodeValue() == 404);
		assertFalse(response.getHeaders().containsKey("userAuthKey"));
		assertTrue(Optional.ofNullable(response.getBody()).isEmpty());
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#register(org.agenda.model.User)}.
	 */
	@Test
	public final void testRegister()
	{
		User user = new User("", "test@test.fr", "test", "test", "test", LocalDate.parse("1985-05-31"));
		ResponseEntity<Void> response = users.register(user);
		assertTrue(response.getStatusCodeValue() == 200);
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#delete(java.lang.String)}.
	 */
	@Test
	public final void testDelete()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getInfo(java.lang.String)}.
	 */
	@Test
	public final void testGetInfo()
	{
		ResponseEntity<User> response = users.loginUser("sophie@fon.fec", "1234");
		assertTrue(response.getStatusCodeValue() == 200);
		User user = response.getBody();

		response = users.getInfo(USER_ID);
		assertTrue(Optional.ofNullable(response.getBody()).isPresent());
		assertTrue(response.getBody().toString().equals(user.toString()));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#updateUser(org.agenda.model.User)}.
	 */
	@Test
	public final void testUpdateUser()
	{
		ResponseEntity<User> response = users.loginUser("sophie@fon.fec", "1234");
		assertTrue(response.getStatusCodeValue() == 200);
		User user = response.getBody();

		String firstName = user.getFirstName();
		user.setFirstName("test");
		response = users.updateUser(user);
		assertTrue(response.getStatusCodeValue() == 200);
		assertTrue(Optional.ofNullable(response.getBody()).isPresent());
		assertTrue(response.getBody().getFirstName().equals(firstName));

		user.setFirstName(firstName);
		users.updateUser(user);
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getDayByDate(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetDayByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getDaysBetweenDates(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetDaysBetweenDates()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getWeekByDate(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeekByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getWeeksByDate(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeeksByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getMonthByDate(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonthByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getMonthsByDate(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonthsByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getYearByDate(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetYearByDate()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.controllers.UserController#getYearsByDate(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetYearsByDate()
	{
		fail("Not yet implemented"); // TODO
	}

}
