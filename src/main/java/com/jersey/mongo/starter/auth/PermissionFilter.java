package com.jersey.mongo.starter.auth;

import com.jersey.mongo.starter.RedirectService;
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
@Priority(Priorities.AUTHORIZATION)
public class PermissionFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Inject
	private UriInfo uriInfo;
	@Inject
	private UserContext userContext;
	@Inject
	private RedirectService redirectService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Permission permission = resourceInfo.getResourceMethod().getAnnotation(Permission.class);
		if (permission == null) {
			permission = resourceInfo.getResourceClass().getAnnotation(Permission.class);
		}

		if (permission != null) {
			if (userContext == null || !userContext.isValid()) {
				throw new IllegalStateException("Something has gone terribly wrong. PermissionFilter shouldn't be accessible for requests from unauthenticated users.");
			}

			if (!userContext.getPermissionTypes().contains(permission.type())) {
				requestContext.abortWith(restricted());
			}
		}
	}

	private Response restricted() {
		return Response.seeOther(redirectService.getRedirectUri().build())
				.build();
	}
}
