/**
 * @since 17 juil. 2019
 */
package org.agenda.data.config;

import java.util.Arrays;

import org.agenda.data.config.filters.UserAuthChecker;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().csrf().disable().authorizeRequests().anyRequest().anonymous();
	}

	@Bean
	public FilterRegistrationBean<UserAuthChecker> mUserAuthChecker()
	{
		FilterRegistrationBean<UserAuthChecker> filterReg = new FilterRegistrationBean<>();
		UserAuthChecker checker = new UserAuthChecker();
		filterReg.setFilter(checker);
		filterReg.setUrlPatterns(Arrays.asList("/users/private/*"));
		filterReg.setOrder(2);

		return filterReg;
	}

}
