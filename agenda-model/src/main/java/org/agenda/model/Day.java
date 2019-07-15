/**
 * @since 30 juin 2019
 */
package org.agenda.model;

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
	public final LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public final void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the occupations
	 */
	public final List<Occupation> getOccupations() {
		return occupations;
	}

	/**
	 * @param occupations the occupations to set
	 */
	public final void setOccupations(List<Occupation> occupations) {
		this.occupations = occupations;
	}

	/**
	 * @return the events
	 */
	public final List<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public final void setEvents(List<Event> events) {
		this.events = events;
	}
	

}
