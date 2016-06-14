package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.RedirectService;
import com.jersey.mongo.starter.auth.CookieFactory;
import com.jersey.mongo.starter.auth.NoAuth;
import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.data.UserDao;
import com.jersey.mongo.starter.data.UserSessionDao;
import com.jersey.mongo.starter.model.User;
import com.jersey.mongo.starter.page.BasePageModel;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 * @author Jacob
 */
@Path("sign-in")
public class SignInResource extends BaseResource {

	@Inject
	private UserSessionDao userSessionDao;
	@Inject
	private UserDao userDao;
	@Inject
	private UriInfo uriInfo;
	@Inject
	private RedirectService redirectService;

	public static class SignInModel extends BasePageModel {

		private final String email;

		public SignInModel(UserContext userContext, String email) {
			super(userContext);
			this.email = email;
		}

		public String getEmail() {
			return email;
		}
	}

	@GET
	@NoAuth(force = true)
	@Template(name = "/sign-in.jsp")
	public SignInModel get(@QueryParam("email") String email) {
		return new SignInModel(getUserContext(), email);
	}

	@POST
	@NoAuth(force = true)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response signIn(@FormParam("email") String email,
						   @FormParam("password") String password) {

		User user = userDao.findUser(email, password);
		if (user != null) {
			return valid(user);
		}
		return invalid(email);
	}

	private Response valid(User user) {
		UUID sessionId = userSessionDao.createSession(user);

		return Response.seeOther(redirectService.getRedirectUri().build())
				.cookie(CookieFactory.signIn(user.getId(), sessionId))
				.build();
	}

	private Response invalid(String email) {
		return Response.seeOther(redirectService.getRedirectUri().
				path("sign-in").
				queryParam("email", email).build()).
				build();
	}
}
