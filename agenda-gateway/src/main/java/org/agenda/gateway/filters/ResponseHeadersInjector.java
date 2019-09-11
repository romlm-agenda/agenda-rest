/**
 * @since 11 sept. 2019
 */
package org.agenda.gateway.filters;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author LE MIERE Romain
 *
 */
public class ResponseHeadersInjector extends ZuulFilter {

	@Override
	public boolean shouldFilter()
	{
		// TODO Implement the method
		return true;
	}

	@Override
	public Object run() throws ZuulException
	{
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
		response.addHeader("Access-Control-Expose-Headers", "userAuthKey");
		return null;
	}

	@Override
	public String filterType()
	{
		// TODO Implement the method
		return "post";
	}

	@Override
	public int filterOrder()
	{
		// TODO Implement the method
		return 10;
	}

}
