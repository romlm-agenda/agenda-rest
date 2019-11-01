/**
 * @since 1 nov. 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author LE MIERE Romain
 *
 */
public class MonthBasedYear extends Year {

	private List<Month> months;

	/**
	 * 
	 */
	public MonthBasedYear() {
		super();
	}

	/**
	 * 
	 */
	public MonthBasedYear(int yearId, List<Month> months) {
		super(yearId);
		this.months = months;
	}

	/**
	 * @return the months
	 */
	public final List<Month> getMonths()
	{
		return months;
	}

	/**
	 * @param months the months to set
	 */
	public final void setMonths(List<Month> months)
	{
		this.months = months;
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

}
