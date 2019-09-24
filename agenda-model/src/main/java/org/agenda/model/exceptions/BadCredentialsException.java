/**
 * @since 24 sept. 2019
 */
package org.agenda.model.exceptions;

/**
 * @author LE MIERE Romain
 *
 */
public class BadCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadCredentialsException(String message) {
		super(message);
	}

}
