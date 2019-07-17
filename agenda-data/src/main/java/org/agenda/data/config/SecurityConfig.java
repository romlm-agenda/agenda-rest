/**
 * @since 17 juil. 2019
 */
package org.agenda.data.config;

import org.agenda.data.config.filters.TokenGateway;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author LE MIERE Romain
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().csrf().disable().authorizeRequests().anyRequest().anonymous();
		http.addFilterAt(new TokenGateway(), BasicAuthenticationFilter.class);
	}

}
