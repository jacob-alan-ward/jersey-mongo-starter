package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.RedirectService;
import com.jersey.mongo.starter.auth.CookieFactory;
import com.jersey.mongo.starter.auth.NoAuth;
import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.data.UserDao;
import com.jersey.mongo.starter.data.UserSessionDao;
import com.jersey.mongo.starter.model.User;
import com.jersey.mongo.starter.page.BasePageModel;
import java.net.URI;
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
@Path("sign-up")
public class SignUpResource extends BaseResource {

	@Inject
	private UserDao userDao;
	@Inject
	private UserSessionDao userSessionDao;
	@Inject
	private UriInfo uriInfo;
	@Inject
	private RedirectService redirectService;

	public static class SignUpModel extends BasePageModel {

		private final String name;
		private final String email;

		public SignUpModel(UserContext userContext, String name, String email) {
			super(userContext);
			this.name = name;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}
	}

	@GET
	@NoAuth(force = true)
	@Template(name = "/sign-up.jsp")
	public SignUpModel get(@QueryParam("name") String name,
						   @QueryParam("email") String email) {
		return new SignUpModel(getUserContext(), name, email);
	}

	@POST
	@NoAuth(force = true)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response signUp(@FormParam("name") String name,
						   @FormParam("email") String email,
						   @FormParam("password") String password) {

		if (userDao.isEmailRegistered(email)) {
			return invalid(name, email);
		}

		User newUser = new User(email, name);
		userDao.createUser(newUser, password);

		return valid(newUser);
	}

	private Response valid(User user) {
		UUID sessionId = userSessionDao.createSession(user);

		return Response.seeOther(redirectService.getRedirectUri().build())
				.cookie(CookieFactory.signIn(user.getId(), sessionId))
				.build();
	}

	private Response invalid(String name, String email) {
		URI redirect = redirectService.getRedirectUri()
				.path("sign-up")
				.queryParam("name", name)
				.queryParam("email", email).build();
		return Response.seeOther(redirect).build();
	}
}
