package com.jersey.mongo.starter.auth;

import java.util.Map;
import java.util.UUID;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

/**
 *
 * @author Jacob
 */
public class CookieFactory {

	private static final String USER_ID_COOKIE = "USER-ID";
	private static final String SESSION_ID_COOKIE = "SESSION-ID";

	private CookieFactory() {
	}

	public static NewCookie[] signIn(String userId, UUID sessionId) {
		return new NewCookie[]{
			new NewCookie(USER_ID_COOKIE, userId),
			new NewCookie(SESSION_ID_COOKIE, sessionId.toString())
		};
	}

	public static NewCookie[] signOut() {
		return new NewCookie[]{
			new NewCookie(USER_ID_COOKIE, ""),
			new NewCookie(SESSION_ID_COOKIE, "")
		};
	}

	public static UserContext getUserContext(Map<String, Cookie> cookies) {
		return new UserContext(getUserId(cookies),
							   getSessionId(cookies));
	}

	private static String getUserId(Map<String, Cookie> cookies) {
		Cookie userIdCookie = cookies.get(USER_ID_COOKIE);
		if (userIdCookie == null) {
			return null;
		}
		return userIdCookie.getValue();
	}

	private static UUID getSessionId(Map<String, Cookie> cookies) {
		Cookie sessionIdCookie = cookies.get(SESSION_ID_COOKIE);
		if (sessionIdCookie == null) {
			return null;
		}
		try {
			return UUID.fromString(sessionIdCookie.getValue());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
