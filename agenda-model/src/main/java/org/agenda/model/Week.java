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
public class Week {

	private int yearId;
	private int weekId;
	private List<Day> days = new ArrayList<>();

	public Week() {
		super();
	}

	public Week(int yearId, int weekId, List<Day> days) {
		super();
		this.yearId = yearId;
		this.weekId = weekId;
		this.days = days;
	}

	/**
	 * @return the yearId
	 */
	public final int getYearId() {
		return yearId;
	}

	/**
	 * @param yearId the yearId to set
	 */
	public final void setYearId(int yearId) {
		this.yearId = yearId;
	}

	/**
	 * @return the weekId
	 */
	public final int getWeekId() {
		return weekId;
	}

	/**
	 * @param weekId the weekId to set
	 */
	public final void setWeekId(int weekId) {
		this.weekId = weekId;
	}

	/**
	 * @return the days
	 */
	public final List<Day> getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public final void setDays(List<Day> days) {
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
