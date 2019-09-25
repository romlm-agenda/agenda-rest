/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author LE MIERE Romain
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic();
		http.formLogin();
		http.csrf().disable();
		http.authorizeRequests().anyRequest().anonymous();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setExposedHeaders(Arrays.asList("userAuthKey", "userId"));
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		Map<String, CorsConfiguration> corsConfigurations = new HashMap<>();
		corsConfigurations.put("/**", corsConfig);
		corsConfigurationSource.setCorsConfigurations(corsConfigurations);
		http.cors().configurationSource(corsConfigurationSource);
	}

}
