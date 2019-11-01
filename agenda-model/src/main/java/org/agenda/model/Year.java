/**
 * @since 30 juin 2019
 */
package org.agenda.model;

/**
 * 
 * @author LE MIERE Romain
 *
 */
public abstract class Year {

	protected int yearId;

	/**
	 * 
	 */
	protected Year() {
	}

	/**
	 * @param yearId
	 */
	protected Year(int yearId) {
		super();
		this.yearId = yearId;
	}

	/**
	 * @return the yearId
	 */
	public final int getYearId()
	{
		return yearId;
	}

	/**
	 * @param yearId the yearId to set
	 */
	public final void setYearId(int yearId)
	{
		this.yearId = yearId;
	}

}
