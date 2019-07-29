/**
 * @since 17 juil. 2019
 */
package org.agenda.data.model.beans.data;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.agenda.model.Event;
import org.agenda.model.Occupation;

/**
 * @author LE MIERE Romain
 *
 */
public class DayBean {

	private LocalDate date;
	private List<Occupation> occupations = new ArrayList<>();
	private List<Event> events = new ArrayList<>();
	private int weekId;

	/**
	 * 
	 */
	public DayBean() {
		super();
	}

	/**
	 * 
	 * @param date
	 * @param occupations
	 * @param events
	 * @param weekId
	 */
	public DayBean(LocalDate date, List<Occupation> occupations, List<Event> events, int weekId) {
		super();
		this.setDate(date);
		this.occupations = occupations;
		this.events = events;
		this.weekId = weekId;
	}

	/**
	 * @return the date
	 */
	public final LocalDate getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public final void setDate(LocalDate date)
	{
		this.date = date;
		WeekFields week = WeekFields.of(Locale.getDefault());
		this.weekId = date.get(week.weekOfWeekBasedYear());
	}

	/**
	 * @return the occupations
	 */
	public final List<Occupation> getOccupations()
	{
		return occupations;
	}

	/**
	 * @param occupations the occupations to set
	 */
	public final void setOccupations(List<Occupation> occupations)
	{
		this.occupations = occupations;
	}

	/**
	 * @return the events
	 */
	public final List<Event> getEvents()
	{
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public final void setEvents(List<Event> events)
	{
		this.events = events;
	}

	/**
	 * @return the weekId
	 */
	public final int getWeekId()
	{
		return weekId;
	}

	/**
	 * @param weekId the weekId to set
	 */
	public final void setWeekId(int weekId)
	{
		this.weekId = weekId;
	}

}
