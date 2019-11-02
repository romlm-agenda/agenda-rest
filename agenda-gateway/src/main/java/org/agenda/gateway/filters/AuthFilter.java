/**
 * @since 27 sept. 2019
 */
package org.agenda.gateway.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author LE MIERE Romain
 *
 */
@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

	private static final String API_KEY = "hello world!";

	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	protected void doFilterInternal(
	    HttpServletRequest request,
	    HttpServletResponse response,
	    FilterChain filterChain
	) throws ServletException, IOException
	{
		Optional<String> apiKeyHeader = Optional.ofNullable(request.getHeader("api-key"));
		if (!request.getRequestURI().contains("/api/")) {
			response.sendError(401, "requested path is not authorized");
			logger.error(request.getRemoteHost()
			        + " IP address requested path "
			        + request.getRequestURI());
		} else if (apiKeyHeader.isEmpty()) {
			response.sendError(401, "missing api-key header");
		} else if (!apiKeyHeader.get().equals(API_KEY)) {
			response.sendError(401, "Invalid api-key provided");
		} else {
			filterChain.doFilter(request, response);
		}

	}

}
