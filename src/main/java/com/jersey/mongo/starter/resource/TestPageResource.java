package com.jersey.mongo.starter.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 * @author Jacob
 */
@Path("test")
public class TestPageResource {

	@GET
	@Template(name = "/test.jsp")
	public String get() {
		return "";
	}
}
