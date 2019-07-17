/**
 * @since 16 juil. 2019
 */
package org.agenda.gateway.filters;

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
public class TokenInjector extends ZuulFilter {

	@Autowired
	private SecurityProxy security;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext.getCurrentContext().addZuulRequestHeader("zuul-security-header", security.getToken());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
