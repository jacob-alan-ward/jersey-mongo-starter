package com.jersey.mongo.starter.resource;

import com.jersey.mongo.starter.auth.NoAuth;
import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.page.BasePageModel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 * @author Jacob
 */
@Path("/")
public class IndexResource extends BaseResource {

	public static class IndexModel extends BasePageModel {

		public IndexModel(UserContext userContext) {
			super(userContext);
		}
	}

	@GET
	@Template(name = "/index.jsp")
	@NoAuth
	public IndexModel get() {
		return new IndexModel(getUserContext());
	}
}
