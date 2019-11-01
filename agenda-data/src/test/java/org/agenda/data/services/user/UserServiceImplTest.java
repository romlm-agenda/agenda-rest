/**
 * @since 28 oct. 2019
 */
package org.agenda.data.services.user;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.agenda.model.Week;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LE MIERE Romain
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Autowired
	private UserService users;

	private static final String USER_ID = "5da1d52ceff9812c30ecc7bc";

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
	 * {@link org.agenda.data.services.user.UserServiceImpl#createUser(org.agenda.model.User)}.
	 */
	@Test
	public final void testCreateUser()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#loginUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testLoginUser()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getInfos(java.lang.String)}.
	 */
	@Test
	public final void testGetInfos()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#deleteUser(java.lang.String)}.
	 */
	@Test
	public final void testDeleteUser()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#updateUser(org.agenda.model.User)}.
	 */
	@Test
	public final void testUpdateUser()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#saveDay(java.lang.String, org.agenda.model.Day)}.
	 */
	@Test
	public final void testSaveDay()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getDay(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetDay()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getDays(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetDays()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#deleteDay(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testDeleteDay()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#deleteDays(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testDeleteDays()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#saveDays(java.lang.String, java.util.List)}.
	 */
	@Test
	public final void testSaveDays()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getWeek(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeek()
	{
		LocalDate date = LocalDate.now();
		Week week = users.getWeek(USER_ID, date);
		int dayOfWeek = date.get(WeekFields.of(Locale.getDefault()).dayOfWeek());
		List<LocalDate> dates = new ArrayList<>();
		for (int i = -dayOfWeek + 1; i <= 7 - dayOfWeek; i++) {
			dates.add(date.plusDays(i));
		}
		assertTrue(dates.equals(week.getDays().stream().map(day -> day.getDate()).collect(Collectors.toList())));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getWeeks(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeeks()
	{
		System.out.println(users.getWeeks(USER_ID, LocalDate.now(), LocalDate.now().plusWeeks(1)));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getMonth(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonth()
	{
		System.out.println(users.getMonth(USER_ID, LocalDate.now()));
		System.out.println(users.getDay(USER_ID, LocalDate.parse("2019-10-12")));
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getMonths(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonths()
	{
		System.out.println(users.getMonths(USER_ID, LocalDate.parse("2019-06-30"), LocalDate.parse("2019-10-10")));
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getYear(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetYear()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getYears(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetYears()
	{
		fail("Not yet implemented"); // TODO
	}

}
