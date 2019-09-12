/**
 * @since 11 sept. 2019
 */
package org.agenda.gateway.filters;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class ResponseHeadersInjector extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(ResponseHeadersInjector.class);

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
        response.addHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE");
		response.addHeader("Access-Control-Expose-Headers", "userAuthKey");
		
		logger.info("Succesfully injected Access-Control headers in response for request "
		        + RequestContext.getCurrentContext().getRequest().getRequestURL().toString());
		
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
