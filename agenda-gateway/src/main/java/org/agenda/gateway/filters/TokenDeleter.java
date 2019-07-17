/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.filters;

import java.util.Optional;

import org.agenda.gateway.proxies.SecurityProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class TokenDeleter extends ZuulFilter {

	@Autowired
	private SecurityProxy security;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		Optional<String> header = Optional
				.ofNullable(RequestContext.getCurrentContext().getZuulRequestHeaders().get("zuul-security-header"));
		if (header.isEmpty() || !security.isTokenValid(header.get())) {
			throw new ZuulException("request forged from an unknown server", 401,
					"invalid or missing header in request");
		}
		security.deleteToken(header.get());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Implement the method
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Implement the method
		return 0;
	}

}
