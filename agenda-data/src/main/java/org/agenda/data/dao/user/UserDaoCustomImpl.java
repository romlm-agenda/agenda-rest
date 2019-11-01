/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.agenda.data.model.beans.data.UserBean;
import org.agenda.data.model.exceptions.UserNotFoundException;
import org.agenda.data.model.mappers.DayMapper;
import org.agenda.model.Day;
import org.agenda.model.User;
import org.bson.Document;
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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;

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
		ProjectionOperation project = Aggregation.project("id", "email", "firstName", "password", "lastName",
		        "birthDate");
		Aggregation aggreg = Aggregation.newAggregation(match, project);
		return mongo.aggregate(aggreg, UserBean.class, User.class);
	}

	// CRUD operations

	@Override
	public User updateUser(User user) throws UserNotFoundException
	{

		Optional<User> oldUserOpt = this.getInfos(user.getId());
		if (oldUserOpt.isEmpty()) {
			throw new UserNotFoundException("user not found for id: "
			        + user.getId());
		}
		Query match = new Query(Criteria.where("id").is(user.getId()));
		Update update = new Update();

		User oldUser = oldUserOpt.get();
		if (!oldUser.getEmail().equals(user.getEmail()))
			update.set("email", user.getEmail());
		if (!oldUser.getFirstName().equals(user.getFirstName()))
			update.set("firstName", user.getFirstName());
		if (!oldUser.getLastName().equals(user.getLastName()))
			update.set("lastName", user.getLastName());
		if (!oldUser.getBirthDate().equals(user.getBirthDate()))
			update.set("birthDate", user.getBirthDate());
		if (!oldUser.getPassword().equals(user.getPassword()))
			update.set("password", user.getPassword());

		if (!update.getUpdateObject().isEmpty())
			mongo.updateFirst(match, update, UserBean.class);

		return oldUser;
	}

	@Override
	public Optional<Day> getDay(
	    String userId,
	    LocalDate date
	)
	{
		ProjectionOperation project = Aggregation.project("days");
		UnwindOperation unwind = Aggregation.unwind("days");
		MatchOperation match = Aggregation.match(Criteria.where("days.date").is(date));
		Aggregation aggregation = prepareAggregation(userId, project, unwind, match);

		AggregationResults<Day> results = mongo.aggregate(aggregation, UserBean.class, Day.class);

		@SuppressWarnings("unchecked")
		List<Document> documents = (List<Document>) results.getRawResults().get("results", List.class);
		if (documents.isEmpty())
			return Optional.empty();
		Document dayDoc = documents.get(0).get("days", Document.class);
		return Optional.ofNullable(DayMapper.mapDay(dayDoc));
	}

	@Override
	public List<Day> getDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		ProjectionOperation project = Aggregation.project("days");
		UnwindOperation unwind = Aggregation.unwind("days");
		MatchOperation match = Aggregation.match(Criteria.where("days.date").gte(from).lte(to));

		Aggregation aggregation = prepareAggregation(userId, project, unwind, match);
		AggregationResults<Day> results = mongo.aggregate(aggregation, UserBean.class, Day.class);

		@SuppressWarnings("unchecked")
		List<Document> documents = (List<Document>) results.getRawResults().get("results", List.class);
		List<Day> days = documents.stream().map(doc -> DayMapper.mapDay(doc.get("days", Document.class)))
		        .collect(Collectors.toList());
		return days;
	}

	@Override
	public Long deleteDay(
	    String userId,
	    LocalDate date
	)
	{
		Query query = new Query(Criteria.where("id").is(userId).and("days.date").is(date));
		Update update = new Update().pull("days", Query.query(Criteria.where("date").is(date)));
		UpdateResult results = mongo.updateFirst(query, update, UserBean.class);

		return results.getModifiedCount();
	}

	@Override
	public Long deleteDays(
	    String userId,
	    LocalDate from,
	    LocalDate to
	)
	{
		Query query = new Query(Criteria.where("id").is(userId).and("days.date").gte(from).lte(to));
		Update update = new Update().pull("days", Query.query(Criteria.where("date").gte(from).lte(to)));
		UpdateResult results = mongo.updateMulti(query, update, UserBean.class);
		return results.getModifiedCount();
	}

	@Override
	public Day saveDay(
	    String userId,
	    Day day
	)
	{
		Query query = new Query(Criteria.where("id").is(userId).and("days.date").is(day.getDate()));
		Update update = new Update().set("days.$", day);

		UpdateResult results = mongo.updateFirst(query, update, UserBean.class);
		if (results.getModifiedCount() == 0) {
			query = new Query(Criteria.where("id").is(userId));
			update = new Update();
			update.push("days", day);

			results = mongo.updateFirst(query, update, UserBean.class);
			if (results.getModifiedCount() == 0)
				throw new UserNotFoundException("the user corresponding to the given id doesn't exist");
		}
		return day;
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
