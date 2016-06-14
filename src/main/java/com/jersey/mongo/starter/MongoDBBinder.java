package com.jersey.mongo.starter;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Jacob
 */
public class MongoDBBinder extends AbstractBinder {

	private static final String DATABASE_NAME = "jersey-mongo-starter";
	private final MongoClient mongoClient;

	public MongoDBBinder(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@Override
	protected void configure() {
		MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
		bind(database).to(MongoDatabase.class);

		DB db = mongoClient.getDB(DATABASE_NAME);
		bind(db).to(DB.class);
	}
}
