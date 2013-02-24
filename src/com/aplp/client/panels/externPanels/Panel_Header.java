package com.aplp.client.panels.externPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Panel_Header implements ExternPanel {


	//###############################################################
	// Singleton pattern
	//###############################################################

	private static Panel_Header instance;

	public static Panel_Header getInstance() {
		synchronized(Panel_Header.class) {
			if (instance == null) {
				instance = new Panel_Header();
			}
		}
		return instance;
	}






	//###############################################################
	// Attributs
	//###############################################################

	private Widget _mainWidget = null;



	//###############################################################
	// Panel methods
	//###############################################################


	@Override
	public Widget getWidget(Context context) {
		if(this._mainWidget == null) {
			this._mainWidget = new Label	("############################################################" +
											"# HEADER		Super forum ma bite ! " +
											"############################################################");
			this._mainWidget.setHeight("10px");
		}
		return this._mainWidget;
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
