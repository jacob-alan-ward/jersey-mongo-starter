package com.jersey.mongo.starter.auth;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Jacob
 */
public class UserContext {

	private final String userId;
	private final UUID sessionId;
	private List<Permission.Type> permissionTypes;

	public UserContext() {
		this(null, null);
	}

	public UserContext(String userId, UUID sessionId) {
		this.userId = userId;
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public List<Permission.Type> getPermissionTypes() {
		return permissionTypes;
	}

	public void setPermissionTypes(List<Permission.Type> permissionTypes) {
		this.permissionTypes = permissionTypes;
	}

	public boolean isValid() {
		return userId != null && sessionId != null;
	}
}
