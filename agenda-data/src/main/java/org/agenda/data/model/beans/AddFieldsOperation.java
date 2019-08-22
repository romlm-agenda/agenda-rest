/**
 * @since 31 juil. 2019
 */
package org.agenda.data.model.beans;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

/**
 * @author LE MIERE Romain
 *
 */
public class AddFieldsOperation implements AggregationOperation {

	private Object updateObject;
	private String field;

	/**
	 * 
	 */
	public AddFieldsOperation(String field, Object update) {
		this.updateObject = update;
		this.field = field;
	}

	@Override
	public Document toDocument(AggregationOperationContext context)
	{
		return new Document("$addFields", new Document(this.field, this.updateObject));
	}

}
