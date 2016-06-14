package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.page.BasePageModel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 * @author Jacob
 */
@Path("profile")
public class ProfileResource extends BaseResource {

	public static class ProfileModel extends BasePageModel {

		public ProfileModel(UserContext userContext) {
			super(userContext);
		}
	}

	@GET
	@Template(name = "/profile.jsp")
	public ProfileModel get() {
		return new ProfileModel(getUserContext());
	}
}
