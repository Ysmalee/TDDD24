package com.aplp.client;

import java.util.Map;

import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.services.Service_ForumAsync;
import com.aplp.client.services.Service_UserAsync;
import com.aplp.shared.businessObjects.User;
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
	
	
	
	/**
	 * Switch to a new panel and call the events methods
	 * COOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL !
	 * @param newPanelEnum New panel enumeration object
	 * @param oldPanelArguments Arguments for the onSetInvisible event on the old panel
	 * @param newPanelArguments Arguments for the onSetVisible event on the new panel
	 */
	public void switchCurrentPanel(PanelsEnum newPanelEnum, Map<String, Object> oldPanelArguments, Map<String, Object> newPanelArguments);


	User getUserConnected();
	void setUserConnected(User user);
	
}
