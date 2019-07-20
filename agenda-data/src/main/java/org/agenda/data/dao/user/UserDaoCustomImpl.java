/**
 * @since 18 juil. 2019
 */
package org.agenda.data.dao.user;

import org.agenda.data.beans.UserBean;
import org.agenda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.authentication.BadCredentialsException;
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
	public User loginUser(String email, String password) throws BadCredentialsException {
		MatchOperation match = Aggregation.match(Criteria.where("email").is(email).and("password").is(password));
		ProjectionOperation project = Aggregation.project("id", "email", "firstName", "lastName", "birthDate");
		Aggregation aggreg = Aggregation.newAggregation(match, project);
		AggregationResults<User> results = mongo.aggregate(aggreg, UserBean.class, User.class);
		if (results.getMappedResults().size() != 1)
			throw new BadCredentialsException("Bad credentials given");
		return results.getUniqueMappedResult();
	}

}
