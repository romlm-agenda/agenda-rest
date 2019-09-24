/**
	 * @since 16 juil. 2019
 */
package org.agenda.data.config;

import org.agenda.data.model.codecs.DayCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	public MongoClientOptions mMongoClientOptions()
	{
		final CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
		        CodecRegistries.fromCodecs(new DayCodec()));
		return MongoClientOptions.builder().codecRegistry(registry).build();
	}

}
