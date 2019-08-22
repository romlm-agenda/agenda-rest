/**
 * @since 29 juin 2019
 */
package org.agenda.model;

/**
 * @author LE MIERE Romain
 *
 */
public class OccupationType {

	private String type;
	private String details;

	/**
	 * 
	 */
	public OccupationType() {
		super();
	}

	public OccupationType(String type, String details) {
		super();
		this.type = type;
		this.details = details;
	}

	/**
	 * @return the type
	 */
	public final String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the details
	 */
	public final String getDetails()
	{
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public final void setDetails(String details)
	{
		this.details = details;
	}

	@Override
	public String toString()
	{
		String s = "{";
		s += "\n\ttype: "
		        + type;
		s += "\n\tdetails: "
		        + details
		        + "\n}";

		return s;
	}

}
