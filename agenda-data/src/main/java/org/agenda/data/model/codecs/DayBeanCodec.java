/**
 * @since 22 août 2019
 */
package org.agenda.data.model.codecs;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.agenda.data.model.beans.data.DayBean;
import org.agenda.model.Event;
import org.agenda.model.Occupation;
import org.bson.BsonArray;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * @author LE MIERE Romain
 *
 */
public class DayBeanCodec implements Codec<DayBean> {

	@Override
	public void encode(
	    BsonWriter writer,
	    DayBean day,
	    EncoderContext encoderContext
	)
	{
		BsonDocument doc = new BsonDocument();

		BsonDateTime date = new BsonDateTime(day.getDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC));
		doc.put("date", date);

		List<BsonValue> occupations = new ArrayList<>();
		for (Occupation occ : day.getOccupations()) {
			BsonDocument obj = new BsonDocument();

			obj.append("beginingTime", new BsonDateTime(
			        LocalDateTime.of(day.getDate(), occ.getBeginingTime()).toEpochSecond(ZoneOffset.UTC)));
			obj.append("endingTime", new BsonDateTime(
			        LocalDateTime.of(day.getDate(), occ.getEndingTime()).toEpochSecond(ZoneOffset.UTC)));

			BsonDocument occupationType = new BsonDocument();
			occupationType.append("type", new BsonString(occ.getOccupationType().getType()));
			occupationType.append("details", new BsonString(occ.getOccupationType().getDetails()));
			obj.append("occupationType", occupationType);

			BsonDocument displayInfos = new BsonDocument();
			displayInfos.append("backgroundColor", new BsonString(occ.getDisplayInfos().getBackgroundColor()));
			displayInfos.append("color", new BsonString(occ.getDisplayInfos().getColor()));
			obj.append("displayInfos", displayInfos);

			occupations.add(obj);
		}
		doc.put("occupations", new BsonArray(occupations));

		List<BsonValue> events = new ArrayList<>();
		for (Event ev : day.getEvents()) {
			BsonDocument obj = new BsonDocument();
			BsonDocument eventType = new BsonDocument();
			eventType.append("type", new BsonString(ev.getEventType().getType()));
			eventType.append("details", new BsonString(ev.getEventType().getDetails()));
			obj.append("eventType", eventType);

			obj.append("eventTime",
			        new BsonDateTime(LocalDateTime.of(day.getDate(), ev.getEventTime()).toEpochSecond(ZoneOffset.UTC)));

			events.add(obj);
		}
		doc.put("events", new BsonArray(events));

		BsonReader reader = new BsonDocumentReader(doc);
		writer.pipe(reader);
		writer.flush();
		reader.close();

	}

	@Override
	public Class<DayBean> getEncoderClass()
	{
		// TODO Implement the method
		return DayBean.class;
	}

	@Override
	public DayBean decode(
	    BsonReader reader,
	    DecoderContext decoderContext
	)
	{
		DayBean day = new DayBean();
		return day;
	}

}
