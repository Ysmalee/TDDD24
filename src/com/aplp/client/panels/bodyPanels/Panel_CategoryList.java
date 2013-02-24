package com.aplp.client.panels.bodyPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Panel_CategoryList implements BodyPanel {

	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_CategoryList instance;

	public static Panel_CategoryList getInstance() {
		synchronized(Panel_CategoryList.class) {
			if (instance == null) {
				instance = new Panel_CategoryList();
			}
		}
		return instance;
	}
	
	
	
	

	//###############################################################
	// Panel methods
	//###############################################################


	@Override
	public Widget getWidget(Context context) {
		return new Label("Category list");
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
