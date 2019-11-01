/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;

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

	@Override
	public String toString()
	{
		String s = this.getClass().getName()
		        + "={";
		for (Field f : this.getClass().getDeclaredFields()) {
			Object o = null;
			try {
				o = f.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			s += "\n\t"
			        + f.getName()
			        + ": "
			        + (o != null ? o.toString() : null)
			        + ",";
		}
		return s
		        + "\n}";
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
