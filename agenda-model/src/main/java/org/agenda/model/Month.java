/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE MIERE Romain
 *
 */
public class Month {

	private int monthId;
	private int yearId;
	private List<Day> days = new ArrayList<>();

	public Month() {
		super();
	}

	public Month(int monthId, int yearId, List<Day> days) {
		super();
		this.monthId = monthId;
		this.yearId = yearId;
		this.days = days;
	}

	/**
	 * @return the monthId
	 */
	public final int getMonthId()
	{
		return monthId;
	}

	/**
	 * @param monthId the monthId to set
	 */
	public final void setMonthId(int monthId)
	{
		this.monthId = monthId;
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
	 * @return the days
	 */
	public final List<Day> getDays()
	{
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public final void setDays(List<Day> days)
	{
		this.days = days;
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
