/**
 * @since 27 sept. 2019
 */
package org.agenda.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	public static final String API_PREFIX = "/api";

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer)
	{
		configurer.addPathPrefix(API_PREFIX, c -> true);
	}

}
