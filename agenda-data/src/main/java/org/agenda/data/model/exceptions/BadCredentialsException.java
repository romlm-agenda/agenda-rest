/**
 * @since 29 sept. 2019
 */
package org.agenda.data.model.exceptions;

/**
 * @author LE MIERE Romain
 *
 */
public class BadCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501397433081397436L;
	
	public BadCredentialsException() {
		super();
	}
	
	public BadCredentialsException(String msg) {
		super(msg);
	}

}
