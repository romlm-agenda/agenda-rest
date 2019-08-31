/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author LE MIERE Romain
 *
 */
public class Year {

	private int yearId;
	private List<Month> months = new ArrayList<>();
	private List<Week> weeks = new ArrayList<>();

	public Year() {
		super();
	}

	public Year(int yearId, List<Month> months, List<Week> weeks) {
		super();
		this.yearId = yearId;
		this.months = months;
		this.weeks = weeks;
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
		String s = "Day={";
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
