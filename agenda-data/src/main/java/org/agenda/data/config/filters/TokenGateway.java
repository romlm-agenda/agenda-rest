/**
 * @since 17 juil. 2019
 */
package org.agenda.data.config.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.data.proxies.SecurityTokenProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author LE MIERE Romain
 *
 */
@Component
@Order(1)
public class TokenGateway extends OncePerRequestFilter {

	@Autowired
	private SecurityTokenProxy securityTokenProxy;

	@Override
	protected void doFilterInternal(
	    HttpServletRequest request,
	    HttpServletResponse response,
	    FilterChain filterChain
	) throws ServletException, IOException
	{
		Optional<String> header = Optional.ofNullable(request.getHeader("zuul-security-header"));
		if (header.isEmpty() || !securityTokenProxy.isTokenValid(header.get()))
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Missing or invalid header");
		filterChain.doFilter(request, response);

	}

}
