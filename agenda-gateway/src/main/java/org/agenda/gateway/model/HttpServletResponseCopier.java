/**
 * @since 8 nov. 2019
 */
package org.agenda.gateway.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author LE MIERE Romain
 *
 */
public class HttpServletResponseCopier extends HttpServletResponseWrapper {

	private ServletOutputStreamCopier stream;
	private PrintWriter writer = null;

	/**
	 * @param response
	 */
	public HttpServletResponseCopier(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException
	{
		if (stream == null) {
			stream = new ServletOutputStreamCopier(getResponse().getOutputStream());
		}
		return stream;
	}

	@Override
	public PrintWriter getWriter() throws IOException
	{
		if (writer == null) {
			writer = new PrintWriter(getOutputStream());
		}
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException
	{
		if (stream != null)
			stream.flush();
		else if (writer != null)
			writer.flush();
		else
			getResponse().getOutputStream().flush();
	}

	public String getBody()
	{
		if (stream != null)
			return String.valueOf(stream.getCopy());
		return null;
	}

}
