/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
public class FeignConfig {

	@Bean
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}

}
