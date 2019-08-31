/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE MIERE Romain
 *
 */
public class Day {

	private LocalDate date;
	private List<Occupation> occupations = new ArrayList<>();
	private List<Event> events = new ArrayList<>();

	/**
	 * 
	 */
	public Day() {
		super();
	}

	public Day(LocalDate date, List<Occupation> occupations, List<Event> events) {
		super();
		this.date = date;
		this.occupations = occupations;
		this.events = events;
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
