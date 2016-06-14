package com.jersey.mongo.starter.data;

import com.jersey.mongo.starter.model.FileContentType;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.jersey.mongo.starter.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import javax.inject.Inject;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Jacob
 */
public class UserDao {

	@Inject
	private MongoDatabase database;
	@Inject
	private DB db;

	public boolean isEmailRegistered(String email) {
		Document existingUser = database.getCollection("users").
				find(Filters.eq("email", email)).
				first();
		return existingUser != null;
	}

	public User findUser(String email,
						 String password) {
		Document document = database.getCollection("users").
				find(Filters.eq("email", email)).
				first();

		if (document != null) {
			String passwordHash = document.getString("passwordHash");
			if (BCrypt.checkpw(password, passwordHash)) {
				return new User(document.getObjectId("_id").toString(),
								document.getString("email"),
								document.getString("name"));
			}
		}

		return null;
	}

	public void createUser(User user,
						   String password) {
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

		Document document = new Document();
		document.put("name", user.getName());
		document.put("email", user.getEmail());
		document.put("passwordHash", passwordHash);

		database.getCollection("users").insertOne(document);

		user.setId(document.getObjectId("_id").toString());
	}

	public void upsertUserImage(String userId,
								FileContentType image) {
		GridFS gridFs = new GridFS(db, "users");

		gridFs.remove(userId);

		GridFSInputFile imageFile = gridFs.createFile(image.getFile());
		imageFile.setFilename(userId);
		imageFile.setMetaData(new BasicDBObject("content_type", image.getContentType()));
		imageFile.save();
		imageFile.validate();
	}

	public FileContentType getUserImage(String userId) {
		GridFS gridFs = new GridFS(db, "users");
		GridFSDBFile file = gridFs.findOne(userId);
		if (file == null) {
			return null;
		}
		return new FileContentType(file.getInputStream(),
								   file.getMetaData().get("content_type").toString());
	}
}
