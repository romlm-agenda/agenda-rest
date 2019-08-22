/**
 * @since 16 juil. 2019
 */
package org.agenda.data.config;

import org.agenda.data.model.codecs.DayBeanCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()
	{
		return new BasicAuthRequestInterceptor("user", "password");
	}

	@Bean
	public MongoClientOptions mMongoClientOptions()
	{
		final CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
		        CodecRegistries.fromCodecs(new DayBeanCodec()));
		return MongoClientOptions.builder().codecRegistry(registry).build();
	}

}
