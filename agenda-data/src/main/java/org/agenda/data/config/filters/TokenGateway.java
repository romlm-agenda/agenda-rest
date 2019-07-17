/**
 * @since 17 juil. 2019
 */
package org.agenda.data.config.filters;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.data.proxies.SecurityProxy;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author LE MIERE Romain
 *
 */
public class TokenGateway extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8611232415010862729L;

	@Inject
	private SecurityProxy security;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException, HttpClientErrorException {
		Optional<String> header = Optional.ofNullable(request.getHeader("zuul-security-header"));
		if (header.isEmpty() || !security.isTokenValid(header.get()))
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Missing or invalid header");
		chain.doFilter(request, response);

	}

}
