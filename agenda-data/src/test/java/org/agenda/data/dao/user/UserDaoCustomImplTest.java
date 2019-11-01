/**
' * @since 31 juil. 2019
 */
package org.agenda.data.dao.user;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.agenda.model.Day;
import org.agenda.model.DisplayInfos;
import org.agenda.model.Occupation;
import org.agenda.model.OccupationType;
import org.agenda.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LE MIERE Romain
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserDaoCustomImplTest {

	public static final String USER_ID = "5da1d52ceff9812c30ecc7bc";

	@Autowired
	private UserDao dao;

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
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#loginUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testLoginUser()
	{
		Optional<User> user = dao.loginUser("sophie@fon.fec", "1234");
		assertTrue(user.isPresent());
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#updateUser(org.agenda.model.User)}.
	 */
	@Test
	public final void testUpdateUser()
	{
		System.out.println("===========================================");
		System.out.println("**********UPDATE USER TEST METHOD**********");
		System.out.println("===========================================");
		Optional<User> userOpt = dao.loginUser("sophie@fon.fec", "1234");
		assertTrue(userOpt.isPresent());

		User user = userOpt.get();
		user.setFirstName("sophie");
		dao.updateUser(user);
		user.setFirstName("Sophie");
		User res = dao.updateUser(user);
		user.setFirstName("sophie");
		assertTrue(user.getFirstName().equals(res.getFirstName()));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#getInfos(java.lang.String)}.
	 */
	@Test
	public final void testGetInfos()
	{
		Optional<User> infoUser = dao.getInfos(USER_ID);
		Optional<User> user = dao.loginUser("sophie@fon.fec", "1234");
		assertTrue(user.isPresent() && infoUser.isPresent());
		System.out.println("===========================================");
		System.out.println("***********GET INFOS TEST METHOD***********");
		System.out.println("===========================================");
		System.out.println(user.get());
		System.out.println(infoUser.get());
		assertTrue(user.get().getId().equals(infoUser.get().getId()));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#saveDay(java.lang.String, org.agenda.model.Day)}.
	 */
	@Test
	public final void testSaveDay()
	{
		Day day = new Day();
		day.setDate(LocalDate.now());
		Occupation occ = new Occupation(new OccupationType("sport", "volley"), LocalTime.now(), LocalTime.now(),
		        new DisplayInfos("black", "white"));
		day.getOccupations().add(occ);
		dao.saveDay(USER_ID, day);
	}

	@Test
	public final void testGetDay()
	{
		LocalDate date = LocalDate.parse("2019-06-06");
		Optional<Day> day = dao.getDay(USER_ID, date);
		System.out.println("===========================================");
		System.out.println("************GET DAY TEST METHOD************");
		System.out.println("===========================================");
		System.out.println(day.isPresent() ? day.get() : null);
	}

	@Test
	public final void testGetDays()
	{
		System.out.println("===========================================");
		System.out.println("***********GET DAYS TEST METHOD************");
		System.out.println("===========================================");
		LocalDate from = LocalDate.parse("2019-10-01");
		LocalDate to = LocalDate.parse("2019-10-31");
		List<Day> days = dao.getDays(USER_ID, from, to);

		System.out.println(days);
	}

	@Test
	public final void testDeleteDay()
	{
		System.out.println("===========================================");
		System.out.println("***********DELETE DAY TEST METHOD**********");
		System.out.println("===========================================");
		LocalDate date = LocalDate.now();
		dao.deleteDay(USER_ID, date);
	}

	@Test
	public final void testDeleteDays()
	{
		LocalDate from = LocalDate.now();
		LocalDate to = LocalDate.now().plusDays(3);
		assertTrue(Long.valueOf((long) dao.getDays(USER_ID, from, to).size()) == dao.deleteDays(USER_ID, from, to));
	}

}
