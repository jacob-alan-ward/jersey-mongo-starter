package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.auth.UserContext;
import javax.inject.Inject;

/**
 *
 * @author Jacob
 */
public abstract class BaseResource {

	@Inject
	private UserContext userContext;

	public UserContext getUserContext() {
		return userContext;
	}
}
