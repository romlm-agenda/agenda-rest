/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.time.LocalTime;

/**
 * @author LE MIERE Romain
 *
 */
public class Event {

	private OccupationType eventType;
	private LocalTime eventTime;

	public Event() {
		super();
	}

	public Event(OccupationType eventType, LocalTime eventTime) {
		super();
		this.eventType = eventType;
		this.eventTime = eventTime;
	}

	/**
	 * @return the eventType
	 */
	public final OccupationType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public final void setEventType(OccupationType eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventTime
	 */
	public final LocalTime getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public final void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

}
