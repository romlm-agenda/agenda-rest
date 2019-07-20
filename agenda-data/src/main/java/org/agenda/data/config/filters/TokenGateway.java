/**
 * @since 17 juil. 2019
 */
package org.agenda.data.config.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.data.proxies.SecurityProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author LE MIERE Romain
 *
 */
public class TokenGateway extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8611232415010862729L;

	@Autowired
	private SecurityProxy security;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException, HttpClientErrorException {
		if (security == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			security = webApplicationContext.getBean(SecurityProxy.class);
		}

		Optional<String> header = Optional.ofNullable(request.getHeader("zuul-security-header"));
		if (header.isEmpty() || !security.isTokenValid(header.get()))
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Missing or invalid header");
		chain.doFilter(request, response);

	}

}
