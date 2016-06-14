package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.RedirectService;
import com.jersey.mongo.starter.auth.CookieFactory;
import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.data.UserSessionDao;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Jacob
 */
@Path("sign-out")
public class SignOutResource extends BaseResource {

	@Inject
	private UserSessionDao userSessionDao;
	@Inject
	private UriInfo uriInfo;
	@Inject
	private RedirectService redirectService;

	@GET
	public Response signOut() {
		UserContext userContext = getUserContext();
		userSessionDao.removeSession(userContext);
		return Response.seeOther(redirectService.getRedirectUri().build())
				.cookie(CookieFactory.signOut())
				.build();
	}
}
