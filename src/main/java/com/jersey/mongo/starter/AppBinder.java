package com.jersey.mongo.starter;

import com.jersey.mongo.starter.auth.UserContext;
import com.jersey.mongo.starter.auth.UserContextFactory;
import com.jersey.mongo.starter.data.PermissionDao;
import com.jersey.mongo.starter.data.UserDao;
import com.jersey.mongo.starter.data.UserSessionDao;
import com.mongodb.MongoClient;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

/**
 *
 * @author Jacob
 */
public class AppBinder extends AbstractBinder {

	private final MongoClient mongoClient;

	public AppBinder(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@Override
	protected void configure() {
		install(new Binder[]{new MongoDBBinder(mongoClient)});

		bind(RedirectService.class).to(RedirectService.class);
		
		bind(UserDao.class).to(UserDao.class);
		bind(UserSessionDao.class).to(UserSessionDao.class);
		bind(PermissionDao.class).to(PermissionDao.class);

		bindFactory(UserContextFactory.class).
				to(UserContext.class).
				proxy(true).
				proxyForSameScope(false).
				in(RequestScoped.class);
	}
}
