package com.aplp.client.panels;

import java.util.Map;

import com.aplp.client.Context;
import com.google.gwt.user.client.ui.Widget;

/**
 * Google peut aller se réhabiller !
 * @author tim
 *
 */
public interface Panel {
	

	
	
	/**
	 * Create the panel widget
	 * @param context Application context
	 * @return The widget representing the panel
	 * @throws Exception Initialization failed :'-(
	 */
	Widget getWidget(Context context) throws Exception;
	
	
	
	/**
	 * Called when the panel is about to be set to visible
	 * @param arguments Named Arguments list
	 */
	void onSetVisible(Map<String, Object> arguments);
	
	
	
	/**
	 * Called when the panel is about to be set to invisible
	 * @param arguments Named Arguments list
	 */
	void onSetInvisible(Map<String, Object> arguments);
	
	
	
	/**
	 * Called when the current panel is replaced by a new one
	 * @param oldPanel Old panel enum
	 * @param oldPanelArguments Arguments received by the old panel
	 * @param newPanel New panel enum
	 * @param newPanelArguments Arguments received by the new panel
	 */
	void onPanelChange(PanelsEnum oldPanel, Map<String, Object> oldPanelArguments, PanelsEnum newPanel, Map<String, Object> newPanelArguments);
	
	
}
