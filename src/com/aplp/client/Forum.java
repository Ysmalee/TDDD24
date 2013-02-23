package com.aplp.client;

import com.aplp.client.services.Service_Forum;
import com.aplp.client.services.Service_ForumAsync;
import com.aplp.client.services.Service_User;
import com.aplp.client.services.Service_UserAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Forum implements EntryPoint {
	
	/**
	 * Services
	 */
	private final Service_UserAsync userService = GWT.create(Service_User.class);
	private final Service_ForumAsync forumService = GWT.create(Service_Forum.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
	}
}
