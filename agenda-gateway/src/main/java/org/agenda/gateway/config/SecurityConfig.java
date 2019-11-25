/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.config;

import java.util.Arrays;

import org.agenda.gateway.filters.UserAuthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author LE MIERE Romain
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties props;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic();
		http.formLogin();
		http.csrf().disable();
		http.authorizeRequests().anyRequest().anonymous();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.cors();
	}

	@Bean
	public FilterRegistrationBean<UserAuthChecker> mUserAuthChecker()
	{
		FilterRegistrationBean<UserAuthChecker> filterReg = new FilterRegistrationBean<>();
		UserAuthChecker checker = new UserAuthChecker();
		filterReg.setFilter(checker);
		filterReg.setUrlPatterns(props.getSecurity().getSecuredPaths());
		filterReg.setOrder(2);
		return filterReg;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("api-key", "userAuthKey", "userId", "Content-Type"));
		configuration.setExposedHeaders(Arrays.asList("api-key", "userAuthKey"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
