package com.aplp.client.panels.externPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
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

	private Context _context;
	private Panel _mainWidget = null;
	private Image _logoutImage = null;



	//###############################################################
	// Panel methods
	//###############################################################


	@Override
	public Widget getWidget(Context context) {
		if(this._mainWidget == null) {
			this._context = context;
			this.initializePanel();
		}
		
		return this._mainWidget;
	}
	
	
	private void initializePanel() {
		this._mainWidget = new VerticalPanel();
		
		//Create the banner
		Image bannerImage = new Image("images/banner.jpg");
		this._mainWidget.add(bannerImage);
		
		//Create the deconnection button
		this._logoutImage = new Image("images/logout.png");
		this._mainWidget.add(this._logoutImage);
		this.updateLogoutImageVisibility();
		
		this._logoutImage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Panel_Header.this._context.setUserConnected(null);
				Panel_Header.this._context.switchCurrentPanel(PanelsEnum.PANEL_LOGIN, null, null);
			}
		});
	}



	@Override
	public void onSetVisible(Map<String, Object> arguments) {

	}



	@Override
	public void onSetInvisible(Map<String, Object> arguments) {

	}



	@Override
	public void onPanelChange(PanelsEnum oldPanel,
			Map<String, Object> oldPanelArguments, PanelsEnum newPanel,
			Map<String, Object> newPanelArguments) {
		this.updateLogoutImageVisibility();
	}
	
	
	private void updateLogoutImageVisibility() {
		this._logoutImage.setVisible(this._context.getUserConnected() != null);
	}

}
