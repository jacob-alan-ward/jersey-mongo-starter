package com.jersey.mongo.starter.auth;

import com.jersey.mongo.starter.data.PermissionDao;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import org.glassfish.hk2.api.Factory;

/**
 *
 * @author Jacob
 */
public class UserContextFactory implements Factory<UserContext> {

	@Context
	private HttpHeaders httpHeaders;
	@Inject
	private PermissionDao permissionDao;

	@Override
	public UserContext provide() {
		UserContext userContext = CookieFactory.getUserContext(httpHeaders.getCookies());
		if (userContext.isValid()) {
			userContext.setPermissionTypes(permissionDao.getPermissionTypes(userContext.getUserId()));
		}
		return userContext;
	}

	@Override
	public void dispose(UserContext t) {

	}
}
