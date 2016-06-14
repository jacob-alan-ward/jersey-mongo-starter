package com.jersey.mongo.starter.page;

import com.jersey.mongo.starter.auth.UserContext;

/**
 *
 * @author Jacob
 */
public class BasePageModel {

	private final UserContext userContext;

	public BasePageModel(UserContext userContext) {
		this.userContext = userContext;
	}

	public UserContext getUserContext() {
		return userContext;
	}

	public boolean isAuthenticated() {
		return userContext != null && userContext.isValid();
	}
}
