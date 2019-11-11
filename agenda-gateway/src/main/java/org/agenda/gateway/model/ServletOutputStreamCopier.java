/**
 * @since 8 nov. 2019
 */
package org.agenda.gateway.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author LE MIERE Romain
 *
 */
public class ServletOutputStreamCopier extends ServletOutputStream {

	private OutputStream out;
	private ByteArrayOutputStream copier;
	
	/**
	 * 
	 */
	public ServletOutputStreamCopier(OutputStream stream) {
		this.out = stream;
		this.copier = new ByteArrayOutputStream();
	}

	@Override
	public void setWriteListener(WriteListener listener)
	{
		// TODO Implement the method

	}

	@Override
	public void write(int b) throws IOException
	{
		out.write(b);
		copier.write(b);

	}

	@Override
	public boolean isReady()
	{
		return true;
	}
	
	public byte[] getCopy()
	{
		return copier.toByteArray();

	}

}
