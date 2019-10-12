/**
 * @since 11 oct. 2019
 */
package org.agenda.data.model.mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.agenda.model.Day;
import org.agenda.model.DisplayInfos;
import org.agenda.model.Event;
import org.agenda.model.Occupation;
import org.agenda.model.OccupationType;
import org.bson.Document;

/**
 * @author LE MIERE Romain
 *
 */
public class DayMapper {
	private DayMapper() {
	}

	@SuppressWarnings("unchecked")
	public final static Day mapDay(Document doc)
	{
		Day day = new Day();
		day.setDate(LocalDate.ofInstant(doc.getDate("date").toInstant(), ZoneId.systemDefault()));
		List<Document> occupationDocs = (List<Document>) doc.get("occupations", List.class);
		day.setOccupations(occupationDocs.stream().map(occ -> mapOccupation(occ)).collect(Collectors.toList()));
		List<Document> eventDocs = (List<Document>) doc.get("events", List.class);
		day.setEvents(eventDocs.stream().map(ev -> mapEvent(ev)).collect(Collectors.toList()));

		return day;
	}

	public static final Occupation mapOccupation(Document doc)
	{
		Occupation occ = new Occupation();
		occ.setBeginingTime(LocalTime.ofInstant(doc.getDate("beginingTime").toInstant(), ZoneId.systemDefault()));
		occ.setEndingTime(LocalTime.ofInstant(doc.getDate("endingTime").toInstant(), ZoneId.systemDefault()));
		occ.setDisplayInfos(mapDisplayInfos(doc.get("displayInfos", Document.class)));
		occ.setOccupationType(mapOccupationType(doc.get("occupationType", Document.class)));

		return occ;
	}

	private static final Event mapEvent(Document doc)
	{
		Event ev = new Event();
		ev.setEventTime(LocalTime.ofInstant(doc.getDate("eventTime").toInstant(), ZoneId.systemDefault()));
		ev.setEventType(mapOccupationType(doc.get("eventType", Document.class)));
		return ev;
	}

	private static final DisplayInfos mapDisplayInfos(Document doc)
	{
		DisplayInfos infos = new DisplayInfos();
		infos.setColor(doc.getString("color"));
		infos.setBackgroundColor(doc.getString("backgroundColor"));

		return infos;
	}

	private static final OccupationType mapOccupationType(Document doc)
	{
		OccupationType type = new OccupationType();
		type.setType(doc.getString("type"));
		type.setDetails(doc.getString("details"));

		return type;
	}

}
