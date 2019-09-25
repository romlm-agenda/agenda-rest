/**
 * @since 24 juil. 2019
 */
package org.agenda.data.config.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.data.proxies.SecurityUserIdProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author LE MIERE Romain
 *
 */
public class UserAuthChecker extends OncePerRequestFilter {

	@Autowired
	private SecurityUserIdProxy securityUserIdProxy;

	@Override
	protected void doFilterInternal(
	    HttpServletRequest request,
	    HttpServletResponse response,
	    FilterChain chain
	) throws IOException, ServletException
	{
		if (securityUserIdProxy == null) {
			WebApplicationContext webApp = WebApplicationContextUtils
			        .getWebApplicationContext(request.getServletContext());
			securityUserIdProxy = webApp.getBean(SecurityUserIdProxy.class);
		}
		
		Optional<String> userIdParam = Optional.ofNullable(request.getHeader("userId"));
		Optional<String> userKeyHeader = Optional.ofNullable(request.getHeader("userAuthKey"));

		if (userIdParam.isEmpty() || userKeyHeader.isEmpty()) {
			String msg = "missing credentials";
			response.sendError(HttpStatus.UNAUTHORIZED.value(), msg);
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, msg);
		}
			
		else if (!securityUserIdProxy.isUserValid(userIdParam.get(), userKeyHeader.get())) {
			String msg = "unvalid credentials";
			response.sendError(HttpStatus.UNAUTHORIZED.value(), msg);
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, msg);
		}
		chain.doFilter(request, response);
	}

}
