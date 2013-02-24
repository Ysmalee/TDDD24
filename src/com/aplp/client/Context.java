package com.aplp.client;

import com.aplp.client.services.Service_ForumAsync;
import com.aplp.client.services.Service_UserAsync;
import com.google.gwt.core.client.EntryPoint;

public interface Context extends EntryPoint {
	
	/**
	 * Return the user service
	 * @return User service object
	 */
	Service_UserAsync getUserService();
	
	/**
	 * Return the forum service
	 * @return Forum service object
	 */
	Service_ForumAsync getForumService();
	
}
