/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.agenda.data.model.beans.AddFieldsOperation;
import org.agenda.data.model.beans.data.DayBean;
import org.agenda.data.model.beans.data.UserBean;
import org.agenda.model.Day;
import org.agenda.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

/**
 * @author LE MIERE Romain
 *
 */
@Component
public class UserDaoCustomImpl implements UserDaoCustom {

	@Autowired
	private MongoTemplate mongo;

	@Override
	public Optional<User> loginUser(
	    String email,
	    String password
	)
	{
		AggregationResults<User> results = getResultsByCriteria(
		        Criteria.where("email").is(email).and("password").is(password));
		return Optional.ofNullable(results.getUniqueMappedResult());
	}

	@Override
	public Optional<User> getInfos(String id)
	{
		if (!ObjectId.isValid(id))
			return Optional.empty();
		AggregationResults<User> results = getResultsByCriteria(Criteria.where("id").is(new ObjectId(id)));
		return Optional.ofNullable(results.getUniqueMappedResult());
	}

	private AggregationResults<User> getResultsByCriteria(Criteria criteria)
	{
		MatchOperation match = Aggregation.match(criteria);
		ProjectionOperation project = Aggregation.project("id", "email", "firstName", "lastName", "birthDate");
		Aggregation aggreg = Aggregation.newAggregation(match, project);
		return mongo.aggregate(aggreg, UserBean.class, User.class);
	}
	
	// CRUD operations

	@Override
	public DayBean saveDay(
	    String userId,
	    DayBean day
	)
	{
		ProjectionOperation project = Aggregation.project("days");
		UnwindOperation unwind = Aggregation.unwind("days");
		MatchOperation matchDate = Aggregation.match(Criteria.where("days.date").is(day.getDate()));
		ProjectionOperation removeWeek = Aggregation.project("days.date", "days.occupations", "days.events");
		AddFieldsOperation update = new AddFieldsOperation("days", day);

		Aggregation aggreg = prepareAggregation(userId, project, unwind, matchDate, removeWeek, update);
		AggregationResults<Day> results = mongo.aggregate(aggreg, UserBean.class, Day.class);
		System.out.println(results.getUniqueMappedResult());

		return day;
	}
	
	


	@Override
	public Optional<DayBean> getDay(
	    String userId,
	    LocalDate date
	)
	{
		// TODO Implement the method
		return null;
	}

	@Override
	public List<DayBean> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		// TODO Implement the method
		return null;
	}
	private Aggregation prepareAggregation(
		String userId,
		AggregationOperation... operations
			)
	{
		MatchOperation matchUser = Aggregation.match(Criteria.where("id").is(userId));
		List<AggregationOperation> list = new ArrayList<>();
		list.add(matchUser);
		list.addAll(Arrays.asList(operations));
		return Aggregation.newAggregation(list);
	}

}
