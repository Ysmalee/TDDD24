package com.aplp.client.panels.bodyPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.user.client.ui.Widget;

public class Panel_Topic implements BodyPanel {

	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_Topic instance;

	public static Panel_Topic getInstance() {
		synchronized(Panel_Topic.class) {
			if (instance == null) {
				instance = new Panel_Topic();
			}
		}
		return instance;
	}
	
	
	
	

	//###############################################################
	// Panel methods
	//###############################################################


	@Override
	public Widget getWidget(Context context) {
		return null;
	}



	@Override
	public void onSetVisible(Map<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onSetInvisible(Map<String, Object> arguments) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onPanelChange(PanelsEnum oldPanel,
			Map<String, Object> oldPanelArguments, PanelsEnum newPanel,
			Map<String, Object> newPanelArguments) {
		// TODO Auto-generated method stub
		
	}
}
