package com.jersey.mongo.starter.data;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.model.User;
import java.util.UUID;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @author Jacob
 */
public class UserSessionDao {

	@Inject
	private MongoDatabase database;

	public UUID createSession(User user) {
		UUID sessionId = UUID.randomUUID();
		database.getCollection("user_sessions").updateOne(Filters.eq("user_id", user.getId()),
														  new Document("$set", new Document("session_id", sessionId.toString())),
														  new UpdateOptions().upsert(true));
		return sessionId;
	}

	public boolean isValidSession(UserContext userContext) {
		Document session = database.getCollection("user_sessions").
				find(Filters.and(Filters.eq("session_id",
											userContext.getSessionId().toString()),
								 Filters.eq("user_id",
											userContext.getUserId()))).first();
		return session != null;
	}

	public void removeSession(UserContext userContext) {
		database.getCollection("sessions").
				deleteOne(Filters.eq("user_id", userContext.getUserId()));
	}
}
