/**
 * @since 28 oct. 2019
 */
package org.agenda.data.services.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.agenda.model.Week;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author LE MIERE Romain
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@Autowired
	private UserService users;

	private static final String USER_ID = "5da1d52ceff9812c30ecc7bc";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
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
		System.out.println("=====================================");
		System.out.println("**************GET WEEK**************");
		System.out.println("=====================================");
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
		System.out.println("=====================================");
		System.out.println("**************GET MONTH**************");
		System.out.println("=====================================");
		System.out.println(users.getMonth(USER_ID, LocalDate.now()));
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getMonths(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonths()
	{
		System.out.println("=====================================");
		System.out.println("**************GET MONTHS*************");
		System.out.println("=====================================");
		System.out.println(users.getMonths(USER_ID, LocalDate.parse("2019-06-30"), LocalDate.parse("2019-10-10")));
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getWeekBasedYear(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeekBasedYear()
	{
		System.out.println("=====================================");
		System.out.println("*********GET WEEK BASED YEAR*********");
		System.out.println("=====================================");
		System.out.println(users.getWeekBasedYear(USER_ID, LocalDate.now()).getWeeks().size());
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getMonthBasedYear(java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonthBasedYear()
	{
		System.out.println("=====================================");
		System.out.println("*********GET MONTH BASED YEAR********");
		System.out.println("=====================================");
		System.out.println(users.getMonthBasedYear(USER_ID, LocalDate.now()).getMonths().size());
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getWeekBasedYears(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetWeekBasedYears()
	{
		System.out.println("=====================================");
		System.out.println("*********GET WEEK BASED YEARS********");
		System.out.println("=====================================");
		System.out
		        .println(users.getWeekBasedYears(USER_ID, LocalDate.parse("2018-06-01"), LocalDate.parse("2019-10-11"))
		                .stream().map(year -> year.getWeeks().size()).reduce((
		                    x,
		                    y) -> x + y));
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.services.user.UserServiceImpl#getMonthBasedYears(java.lang.String, java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public final void testGetMonthBasedYears()
	{
		System.out.println("=====================================");
		System.out.println("*********GET MONTH BASED YEARS*******");
		System.out.println("=====================================");
		System.out
		        .println(users.getMonthBasedYears(USER_ID, LocalDate.parse("2018-06-01"), LocalDate.parse("2019-10-11"))
		                .stream().map(year -> year.getMonths().size()).reduce((
		                    x,
		                    y) -> x + y));
	}

}
