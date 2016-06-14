package com.jersey.mongo.starter.data;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.jersey.mongo.starter.auth.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @author Jacob
 */
public class PermissionDao {

	@Inject
	private MongoDatabase database;

	public List<Permission.Type> getPermissionTypes(String userId) {
		List<Permission.Type> permissionTypes = new ArrayList<>();

		database.getCollection("user_permissions").
				find(Filters.eq("user_id", userId)).
				forEach((Consumer<Document>) document -> {
					permissionTypes.add(Permission.Type.valueOf(document.getString("permission_type")));
				});

		return permissionTypes;
	}

	public void grantPermission(String userId,
								Permission.Type permissionType) {
		database.getCollection("user_permissions").
				insertOne(new Document().
						append("user_id", userId).
						append("permission_type", permissionType.name()));
	}
}
