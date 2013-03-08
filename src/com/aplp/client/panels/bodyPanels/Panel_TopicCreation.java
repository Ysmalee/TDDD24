package com.aplp.client.panels.bodyPanels;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel_TopicCreation implements BodyPanel {
	
	private Panel _panel_main;
	private Context _context;
	private TextBox _textBoxTitle;
	private TextArea _textAreaMessage;
	private Label _label_error;
	private Category _category;

	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_TopicCreation instance;

	public static Panel_TopicCreation getInstance() {
		synchronized(Panel_TopicCreation.class) {
			if (instance == null) {
				instance = new Panel_TopicCreation();
			}
		}
		return instance;
	}

	
	

	//###############################################################
	// Panel methods
	//###############################################################


	@Override
	public Widget getWidget(Context context) {
		if(this._panel_main == null) {
			this._context = context;
			this.initializeWidget();
		}
		return this._panel_main;
	}



	private void initializeWidget() {
		//Create the fields
		this._textBoxTitle = new TextBox();
		this._textAreaMessage = new TextArea();
		
		this._textAreaMessage.setPixelSize(300, 200);
		
		// Create the labels
		Label labelTitle = new Label("Title");
		Label labelMessage = new Label("Message");

		//Create the field panel
		Panel fieldsPanel = new VerticalPanel();
		fieldsPanel.add(labelTitle);
		fieldsPanel.add(this._textBoxTitle);
		fieldsPanel.add(labelMessage);
		fieldsPanel.add(this._textAreaMessage);
		
		//Create the error label
		this._label_error = new Label("");
		this._label_error.setVisible(false);

		//Create the login button
		Button buttonCreate = new Button();
		buttonCreate.setText("Create");
		buttonCreate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Panel_TopicCreation.this.createTopic(Panel_TopicCreation.this._textBoxTitle.getText(), Panel_TopicCreation.this._textAreaMessage.getText());
			}
		});
		
		//Create the button panel
		Panel buttonPanel = new VerticalPanel();
		buttonPanel.add(this._label_error);
		buttonPanel.add(buttonCreate);
		
		//Create the main panel
		this._panel_main = new VerticalPanel();
		this._panel_main.add(fieldsPanel);
		this._panel_main.add(buttonPanel);

		//Clear the fields
		this.clearWidgets();
		
	}



	protected void createTopic(String title, String message) {
		Date d = new Date();
		User u = this._context.getUserConnected();
		Topic topic = new Topic(title, message, d, u.get_id() , this._category.get_id());
		this._context.getForumService().createTopic(topic, new AsyncCallback<Topic>() {
			@Override
			public void onSuccess(Topic result) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("category", Panel_TopicCreation.this._category);
				Panel_TopicCreation.this._context.switchCurrentPanel(PanelsEnum.PANEL_TOPIC_LIST, null, params);
			}
			@Override
			public void onFailure(Throwable caught) {
				Panel_TopicCreation.this._label_error.setText(caught.getMessage());
				Panel_TopicCreation.this._label_error.setVisible(true);				
			}

		});
		this.clearWidgets();
		
	}


	private void clearWidgets() {
		this._textBoxTitle.setText("");
		this._textAreaMessage.setText("");
		this._label_error.setText("");
		this._label_error.setVisible(false);
	}


	@Override
	public void onSetVisible(Map<String, Object> arguments) {
		this._category = (Category)arguments.get("category");
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
