/**
 * @since 1 nov. 2019
 */
package org.agenda.model;

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

}
