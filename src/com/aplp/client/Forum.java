package com.aplp.client;

import java.util.HashMap;
import java.util.Map;

import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.panels.externPanels.ExternPanel;
import com.aplp.client.panels.externPanels.Panel_Footer;
import com.aplp.client.panels.externPanels.Panel_Header;
import com.aplp.client.services.Service_Forum;
import com.aplp.client.services.Service_ForumAsync;
import com.aplp.client.services.Service_User;
import com.aplp.client.services.Service_UserAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Forum implements Context {

	//###############################################################
	// Constants
	//###############################################################

	/**
	 * Log ID constant
	 */
	private static final String LOG_ID = "Forum";


	/**
	 * HTML id of the header bloc
	 */
	private static final String PANEL_ID_HEADER = "header";

	/**
	 * HTML id of the body bloc
	 */
	private static final String PANEL_ID_BODY = "body";

	/**
	 * HTML id of the footer bloc
	 */
	private static final String PANEL_ID_FOOTER = "footer";





	//###############################################################
	// Attributs
	//###############################################################

	/**
	 * Header panel
	 */
	private ExternPanel _headerPanel;
	
	/**
	 * Deck panel
	 */
	private DeckPanel _deckPanel;

	/**
	 * Footer panel
	 */
	private ExternPanel _footerPanel;
	
	
	
	
	/**
	 * Services
	 */
	private final Service_UserAsync _userService = GWT.create(Service_User.class);
	private final Service_ForumAsync _forumService = GWT.create(Service_Forum.class);






	//###############################################################
	// Module methods
	//###############################################################

	/**
	 * This is the entry point method.
	 * C'est ici que tout commence
	 */
	public void onModuleLoad() {
		//Create the UI part
		try {
			this.initializePanels();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}






	//###############################################################
	// Object methods
	//###############################################################

	/**
	 * Initialize all the panels
	 * @throws Exception
	 */
	private void initializePanels() throws Exception {
		this.initializeHeaderPanel();
		this.initializeFooterPanel();
		this.initializeDeckPanel();
	}



	/**
	 * Initialize the header panel
	 * @throws Exception
	 */
	private void initializeHeaderPanel() throws Exception {
		//Create the header panel
		this._headerPanel = new Panel_Header();
		Widget widget = this._headerPanel.getWidget(this);

		//Put the panel into the HTML page
		if(widget != null) {
			RootPanel.get(PANEL_ID_HEADER).add(widget);
		}
	}



	/**
	 * Initialize the footer panel
	 * @throws Exception
	 */
	private void initializeFooterPanel() throws Exception {
		//Create the header panel
		this._footerPanel = new Panel_Footer();
		Widget widget = this._footerPanel.getWidget(this);

		//Put the panel into the HTML page
		if(widget != null) {
			RootPanel.get(PANEL_ID_FOOTER).add(widget);
		}
	}





	/**
	 * Initialize the panels of body of the application (attribute: this._deckPanel)
	 * pwet !
	 * @throws Exception suce !
	 */
	private void initializeDeckPanel() throws Exception {
		//Create the deck panel
		DeckPanel deckPanel = new DeckPanel();

		//Create and register the panels
		for (PanelsEnum panelEnum : PanelsEnum.values()) {
			try {
				Widget panelWidget = panelEnum.getPanel().getWidget(this);
				if(panelWidget == null) {
					panelWidget = new Label("The panel \"" + panelEnum.name() + "\" is not designed yet !");
				}
				deckPanel.add(panelWidget);
			} catch(Exception e) {
				throw new Exception("Fail to initialize the panel \"" + panelEnum.name() + "\"", e);
			}
		}

		//Select the login panel
		if(PanelsEnum.values().length > 0) {
			deckPanel.showWidget(0);
		} else {
			throw new Exception("No panels available");
		}

		//Put the deck panel into the HTML page
		this._deckPanel = deckPanel;
		RootPanel.get(PANEL_ID_BODY).add(this._deckPanel);
	}





	@Override
	public void switchCurrentPanel(PanelsEnum newPanelEnum, Map<String, Object> oldPanelArguments, Map<String, Object> newPanelArguments) {
		//Precondition
		if(newPanelEnum == null) {
			throw new IllegalArgumentException("The \"newPanel\" argument must not be null");
		}
		if(oldPanelArguments == null) {
			oldPanelArguments = new HashMap<String, Object>();
		}
		if(newPanelArguments == null) {
			newPanelArguments = new HashMap<String, Object>();
		}


		//Get the current panel
		int currentPanelId = this._deckPanel.getVisibleWidget();
		PanelsEnum oldPanelEnum = PanelsEnum.values()[currentPanelId];

		//Call the onSetInvisible event
		oldPanelEnum.getPanel().onSetInvisible(new HashMap<String, Object>(oldPanelArguments));

		//Call the onSetVisible event CONNARD
		newPanelEnum.getPanel().onSetVisible(new HashMap<String, Object>(newPanelArguments));

		//Call the on switch event
		if(this._headerPanel != null) {
			this._headerPanel.onPanelChange(oldPanelEnum, oldPanelArguments, newPanelEnum, newPanelArguments);
		}
		if(this._footerPanel != null) {
			this._footerPanel.onPanelChange(oldPanelEnum, oldPanelArguments, newPanelEnum, newPanelArguments);
		}
		

		//Switch visibility
		for(int i=0; i<PanelsEnum.values().length; i++) {
			if(PanelsEnum.values()[i].equals(newPanelEnum)) {
				this._deckPanel.showWidget(i);
			}
		}

	}






	@Override
	public Service_UserAsync getUserService() {
		return this._userService;
	}






	@Override
	public Service_ForumAsync getForumService() {
		return this._forumService;
	}






}
