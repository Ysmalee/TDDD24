package com.aplp.client.panels.bodyPanels;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel_Login implements BodyPanel {

	private Context _context;
	private Panel _panel_main;
	
	private TextBox _textbox_username;
	private TextBox _textbox_password;
	private Label _label_error;


	
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
	public Widget getWidget(final Context context) {
		if(this._panel_main == null) {
			this._context = context;
			this.initializeWidget();
		}
		return this._panel_main;
	}


	/**
	 * Initialize the main panel
	 */
	private void initializeWidget() {
		//Create the fields
		this._textbox_username = new TextBox();
		this._textbox_password = new PasswordTextBox();

		//Create the field panel
		Panel fieldsPanel = new VerticalPanel();
		fieldsPanel.add(this._textbox_username);
		fieldsPanel.add(this._textbox_password);
		
		//Create the error label
		this._label_error = new Label("");
		this._label_error.setVisible(false);

		//Create the login button
		Button button_login = new Button();
		button_login.setText("Login");
		button_login.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Panel_Login.this.loginAttempt(Panel_Login.this._textbox_username.getText(), Panel_Login.this._textbox_password.getText());
			}
		});
		
		//Create the button panel
		Panel buttonPanel = new VerticalPanel();
		buttonPanel.add(this._label_error);
		buttonPanel.add(button_login);
		
		//Create the main panel
		this._panel_main = new VerticalPanel();
		this._panel_main.add(fieldsPanel);
		this._panel_main.add(buttonPanel);

		//Clear the fields
		this.clearWidgets();
	}

	
	/**
	 * Login attempt with the current username and password
	 */
	private void loginAttempt(String username, String password) {
		this._context.getUserService().login(username, password, new AsyncCallback<Boolean>() {			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true) {
					Panel_Login.this._label_error.setVisible(false);
					Panel_Login.this._context.switchCurrentPanel(PanelsEnum.PANEL_CATEGORY_LIST, null, null);
				} else {
					Panel_Login.this._label_error.setText("Username or password incorrect");
					Panel_Login.this._label_error.setVisible(true);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Panel_Login.this._label_error.setText(caught.getMessage());
				Panel_Login.this._label_error.setVisible(true);
			}
		});
	}
	
	
	
	/**
	 * Reset the fields text/visibility
	 */
	private void clearWidgets() {
		this._textbox_username.setText("");
		this._textbox_password.setText("");
		Panel_Login.this._label_error.setText("");
		Panel_Login.this._label_error.setVisible(false);
	}



	@Override
	public void onSetVisible(Map<String, Object> arguments) {
		this.clearWidgets();
	}



	@Override
	public void onSetInvisible(Map<String, Object> arguments) {
		this.clearWidgets();
	}



	@Override
	public void onPanelChange(PanelsEnum oldPanel,
			Map<String, Object> oldPanelArguments, PanelsEnum newPanel,
			Map<String, Object> newPanelArguments) {

	}
}
