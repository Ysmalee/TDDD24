package com.aplp.client.panels.bodyPanels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.widgets.Widget_Category;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Topic;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel_TopicList implements BodyPanel {
	

	private Panel _panel_main;
	private Context _context;
	private Label _labelError;
	private Label _labelEmpty;
	private Category _category;
	private HTML _back;
	private Button _createButton;

	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_TopicList instance;

	public static Panel_TopicList getInstance() {
		synchronized(Panel_TopicList.class) {
			if (instance == null) {
				instance = new Panel_TopicList();
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
		return this	._panel_main;
	}


	private void initializeWidget() {
		//Create the main panel
		this._panel_main = new VerticalPanel();
		//Create the labels
		this._labelError = new Label("");
		this._labelError.setVisible(false);
		this._labelEmpty = new Label("");
		
		//Create the back link
		this._back = new HTML("<a href=\"javascript:undefined;\">Back to the category list</a>");
		this._back.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Panel_TopicList.this._context.switchCurrentPanel(PanelsEnum.PANEL_CATEGORY_LIST, null, null);
			}
		});
		this._panel_main.add(this._back);
				
		//Create the create button
		_createButton = new Button("New topic");
		_createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("category", Panel_TopicList.this._category);
				Panel_TopicList.this._context.switchCurrentPanel(PanelsEnum.PANEL_TOPIC_CREATION, null, params);
			}
		});
		
		this._panel_main.add(this._createButton);
	}


	@Override
	public void onSetVisible(Map<String, Object> arguments) {
		this._category = (Category)arguments.get("category");
		this.displayTopics(); // Le onSetVisible est callé qu'après initWidget du coup on peut se servir de l'argument que là...
		this.clearWidgets();
	}



	private void displayTopics() {
		this._context.getForumService().getTopics(this._category, new AsyncCallback<List<Topic>>() {
		
		@Override
		public void onSuccess(List<Topic> result) {
			if (result.isEmpty()) {
				Panel_TopicList.this._labelEmpty.setText("There is no topic right now, create one!");
				Panel_TopicList.this._panel_main.add(Panel_TopicList.this._labelEmpty);
			} else {
				for(Topic t : result) {
					//TODO Mettre une FlexTable au lieu de simples liens
					Panel_TopicList.this._panel_main.add(new Label("<a href=\"javascript:undefined;\">" + t.get_title() + "</a>"));
				}
			}
		}
		
		@Override
		public void onFailure(Throwable caught) {
			Panel_TopicList.this._labelError.setText(caught.getMessage());
			Panel_TopicList.this._labelError.setVisible(true);				
		}
	});
		
	}




	@Override
	public void onSetInvisible(Map<String, Object> arguments) {
		this.clearWidgets();		
	}



	private void clearWidgets() {
		this._labelError.setText("");
		this._labelError.setVisible(false);
		this._labelEmpty.setText("");
	}




	@Override
	public void onPanelChange(PanelsEnum oldPanel,Map<String, Object> oldPanelArguments, PanelsEnum newPanel, Map<String, Object> newPanelArguments) {
	}
}
