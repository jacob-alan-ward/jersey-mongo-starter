package com.jersey.mongo.starter.auth;

import com.jersey.mongo.starter.RedirectService;
import com.jersey.mongo.starter.data.UserSessionDao;
import java.io.IOException;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Jacob
 */
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Inject
	private UriInfo uriInfo;
	@Inject
	private UserSessionDao userSessionDao;
	@Inject
	private UserContext userContext;
	@Inject
	private RedirectService redirectService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		NoAuth noAuth = resourceInfo.getResourceMethod().getAnnotation(NoAuth.class);
		if (noAuth == null) {
			noAuth = resourceInfo.getResourceClass().getAnnotation(NoAuth.class);
		}

		if (noAuth == null || noAuth.force()) {
			if (noAuth == null) { // user has to be authenticated to view the resource
				if (userContext != null && userContext.isValid()) {
					if (userSessionDao.isValidSession(userContext)) {
						// valid session
						return;
					}
				}
				requestContext.abortWith(unauthenticated());
			} else if (noAuth.force()) { // user has to be un-authenticated to view the resource
				if (userContext != null && userContext.isValid()) {
					requestContext.abortWith(alreadyAuthenticated());
				}
			}
		}
	}

	private Response unauthenticated() {
		return Response.seeOther(redirectService.getRedirectUri().path("sign-in").build())
				.cookie(CookieFactory.signOut())
				.build();
	}

	private Response alreadyAuthenticated() {
		return Response.seeOther(uriInfo.getBaseUri())
				.build();
	}
}
