/**
 * @since 31 juil. 2019
 */
package org.agenda.data.dao.user;

import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.agenda.data.model.beans.data.DayBean;
import org.agenda.data.model.codecs.DayBeanCodec;
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
			        CodecRegistries.fromCodecs(new DayBeanCodec()));
			return MongoClientOptions.builder().codecRegistry(registry).build();

		}
	}

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
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#getInfos(java.lang.String)}.
	 */
	@Test
	public final void testGetInfos()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link org.agenda.data.dao.user.UserDaoCustomImpl#saveDay(java.lang.String, org.agenda.model.Day)}.
	 */
	@Test
	public final void testSaveDay()
	{
		DayBean day = new DayBean();
		String id = "5d3aeb30cb3e9d2f6c30c081";
		day.setDate(LocalDate.parse("2019-06-06"));
		dao.saveDay(id, day);
	}

}
