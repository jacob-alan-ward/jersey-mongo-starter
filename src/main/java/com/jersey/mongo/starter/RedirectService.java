package com.jersey.mongo.starter;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Jacob
 */
public class RedirectService {

	@Context
	private UriInfo uriInfo;

	public UriBuilder getRedirectUri() {
		return uriInfo.getBaseUriBuilder().path("/");
	}
}
