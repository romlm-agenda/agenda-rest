/**
 * @since 1 nov. 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE MIERE Romain
 *
 */
public class WeekBasedYear extends Year {

	private List<Week> weeks = new ArrayList<>();

	/**
	 * 
	 */
	public WeekBasedYear() {
		super();
	}

	/**
	 * 
	 */
	public WeekBasedYear(int yearId, List<Week> weeks) {
		super(yearId);
		this.weeks = weeks;
	}

	/**
	 * @return the weeks
	 */
	public final List<Week> getWeeks()
	{
		return weeks;
	}

	/**
	 * @param weeks the weeks to set
	 */
	public final void setWeeks(List<Week> weeks)
	{
		this.weeks = weeks;
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
