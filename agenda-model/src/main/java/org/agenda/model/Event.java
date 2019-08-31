/**
 * @since 30 juin 2019
 */
package org.agenda.model;

import java.lang.reflect.Field;
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
	public final OccupationType getEventType()
	{
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public final void setEventType(OccupationType eventType)
	{
		this.eventType = eventType;
	}

	/**
	 * @return the eventTime
	 */
	public final LocalTime getEventTime()
	{
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public final void setEventTime(LocalTime eventTime)
	{
		this.eventTime = eventTime;
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
