package org.agenda.gateway.filters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringJoiner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agenda.gateway.model.HttpServletResponseCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(0)
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(
	    HttpServletRequest request,
	    HttpServletResponse response,
	    FilterChain filterChain
	) throws ServletException, IOException
	{
		HttpServletResponseCopier responseWrapper = new HttpServletResponseCopier(response);
		filterChain.doFilter(request, responseWrapper);

		LOG.info(describeContext(request, responseWrapper));

	}

	private String describeContext(
	    HttpServletRequest request,
	    HttpServletResponseCopier responseWrapper
	)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"request\": ");
		builder.append(describeRequest(request));
		builder.append(", \"response\": ");
		builder.append(describeResponse(responseWrapper));
		builder.append(", \"context\": {");
		builder.append("\"time\": \"").append(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
		        .append("\", ");
		builder.append("\"client_locale\": \"").append(request.getLocale()).append("\", ");
		builder.append("\"server\": \"").append(request.getLocalName()).append(":").append(request.getLocalPort());
		builder.append("\"}}");
		return builder.toString();
	}

	private String describeRequest(HttpServletRequest request)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"path\": \"");
		builder.append(request.getRequestURI());
		builder.append("\", \"method\": \"");
		builder.append(request.getMethod());
		builder.append("\", \"headers\": {");
		StringJoiner headerJoiner = new StringJoiner(",");

		Enumeration<String> headersName = request.getHeaderNames();
		while (headersName.hasMoreElements()) {
			String headerName = headersName.nextElement();
			Enumeration<String> headersValue = request.getHeaders(headerName);
			StringJoiner value = new StringJoiner(";");
			while (headersValue.hasMoreElements()) {
				value.add(headersValue.nextElement());
			}

			headerJoiner.add("\""
			        + headerName
			        + "\": \""
			        + value.toString().replace("\"", "\'")
			        + "\"");
		}

		builder.append(headerJoiner);
		builder.append("}, \"parameters\": {");

		StringJoiner paramJoiner = new StringJoiner(",");
		for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
			List<String> paramValues = Arrays.asList(param.getValue());
			String value = "\""
			        + param.getKey()
			        + "\": ";
			if (paramValues.isEmpty())
				value += "\"null\"";
			else if (paramValues.size() == 1)
				value += "\""
				        + paramValues.get(0)
				        + "\"";
			else
				value += param.toString();

			paramJoiner.add(value);
		}
		builder.append(paramJoiner);
		builder.append("}, \"client\": \"");
		builder.append(request.getRemoteHost()).append(":").append(request.getRemotePort());
		builder.append("\"}");
		return builder.toString();
	}

	private String describeResponse(HttpServletResponseCopier response)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"status\": ");
		builder.append(response.getStatus());
		builder.append(", \"headers\": {");

		StringJoiner headerJoiner = new StringJoiner(",");

		for (String headerName : new HashSet<String>(response.getHeaderNames())) {
			StringJoiner value = new StringJoiner(";");
			for (String headerValues : response.getHeaders(headerName))
				value.add(headerValues);

			headerJoiner.add("\""
			        + headerName
			        + "\": \""
			        + value.toString().replace("\"", "\'")
			        + "\"");
		}

		builder.append(headerJoiner);
		builder.append("}, \"body\": ");

		String body = response.getBody();
		if (body == null)
			builder.append("null");
		else if (body.startsWith("{") && body.endsWith("}"))
			builder.append(body);
		else
			builder.append("\"").append(body).append("\"");

		builder.append("}");
		return builder.toString();
	}

}