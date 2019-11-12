/**
 * @since 24 juil. 2019
 */
package org.agenda.gateway.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.gateway.proxies.SecurityUserIdProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	private static final Logger LOG = LoggerFactory.getLogger(UserAuthChecker.class);

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
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "missing credentials");
			LOG.warn(request.getRemoteHost()+" IP address requested "+request.getRequestURL()+" and throw error \"missing credentials\"");
		} else if (!securityUserIdProxy.isUserValid(userIdParam.get(), userKeyHeader.get())) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid credentials");
			LOG.warn(request.getRemoteHost()+" IP address requested "+request.getRequestURL()+" and throw error \"invalid credentials\"");
		} else {
			chain.doFilter(request, response);
		}
	}

}
