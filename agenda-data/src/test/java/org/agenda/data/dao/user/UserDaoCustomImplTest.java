/**
' * @since 31 juil. 2019
 */
package org.agenda.data.dao.user;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.codecs.DayCodec;
import org.agenda.model.Day;
import org.agenda.model.DisplayInfos;
import org.agenda.model.Occupation;
import org.agenda.model.OccupationType;
import org.agenda.model.User;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * @author LE MIERE Romain
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserDaoCustomImplTest {

	@TestConfiguration
	public static class MongoConfiguration {
		@Bean
		public MongoClientOptions mongoClientOptions()
		{
			final CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
			        CodecRegistries.fromCodecs(new DayCodec()));
			return MongoClientOptions.builder().codecRegistry(registry).build();

		}
	}

	public static final String USER_ID = "5d3aeb30cb3e9d2f6c30c081";

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
		assertTrue(user.get().getId().equals(USER_ID));
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
		assertTrue(user.get().equals(infoUser.get()));

	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#saveDay(java.lang.String, org.agenda.model.Day)}.
	 */
	@Test
	public final void testSaveDay()
	{
		Day day = new Day();
		day.setDate(LocalDate.parse("2019-06-06"));
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
		LocalDate from = LocalDate.parse("2019-06-06");
		LocalDate to = LocalDate.parse("2019-06-28");
		List<Day> days = dao.getDays(USER_ID, from, to);
		System.out.println("===========================================");
		System.out.println("***********GET DAYS TEST METHOD************");
		System.out.println("===========================================");
		System.out.println(days);
	}

	@Test
	public final void testDeleteDay()
	{
		LocalDate date = LocalDate.now();
		assertTrue((dao.getDay(USER_ID, date).isPresent() ? 1 : 0) == dao.deleteDay(USER_ID, date));
	}

	@Test
	public final void testDeleteDays()
	{
		LocalDate from = LocalDate.now();
		LocalDate to = LocalDate.now().plusDays(3);
		assertTrue(Long.valueOf((long) dao.getDays(USER_ID, from, to).size()) == dao.deleteDays(USER_ID, from, to));
	}

}
