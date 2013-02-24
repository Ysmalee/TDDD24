package com.aplp.client.panels.bodyPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.user.client.ui.Widget;

public class Panel_Login implements BodyPanel {

	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_Login instance;

	public static Panel_Login getInstance() {
		synchronized(Panel_Login.class) {
			if (instance == null) {
				instance = new Panel_Login();
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
