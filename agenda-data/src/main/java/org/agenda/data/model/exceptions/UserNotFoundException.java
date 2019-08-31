/**
 * @since 31 août 2019
 */
package org.agenda.data.model.exceptions;

import com.mongodb.MongoException;

/**
 * @author LE MIERE Romain
 *
 */
public class UserNotFoundException extends MongoException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
