package com.jersey.mongo.starter;

import com.jersey.mongo.starter.auth.AuthenticationFilter;
import com.jersey.mongo.starter.auth.PermissionFilter;
import com.mongodb.MongoClient;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.servlet.ServletProperties;

/**
 *
 * @author Jacob
 */
public class AppConfig extends ResourceConfig implements ContainerLifecycleListener {

	private final MongoClient mongoClient;

	public AppConfig() {
		register(MvcFeature.class);
		register(JspMvcFeature.class);
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/template");

		register(MultiPartFeature.class);

		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
		register(PermissionFilter.class);

		property(ServletProperties.FILTER_FORWARD_ON_404, true);
		property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/.*\\.(html|css|jpg|gif|png|js|otf|eot|svg|ttf|woff)");

		packages("com.jersey.mongo.starter.resource");

		// database configuration
		mongoClient = new MongoClient("localhost", 27017);

		register(new AppBinder(mongoClient));
	}

	@Override
	public void onStartup(Container container) {
	}

	@Override
	public void onReload(Container container) {
	}

	@Override
	public void onShutdown(Container container) {
		mongoClient.close();
	}
}
