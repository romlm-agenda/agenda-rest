/**
 * @since 29 juin 2019
 */
package org.agenda.model;

/**
 * @author LE MIERE Romain
 *
 */
public class OccupationType {

	// ex : Sport
	private String Type;
	// ex : volley-ball
	private String details;

	/**
	 * 
	 */
	public OccupationType() {
		super();
	}

	public OccupationType(String type, String details) {
		super();
		Type = type;
		this.details = details;
	}

	/**
	 * @return the type
	 */
	public final String getType() {
		return Type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		Type = type;
	}

	/**
	 * @return the details
	 */
	public final String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public final void setDetails(String details) {
		this.details = details;
	}

}
