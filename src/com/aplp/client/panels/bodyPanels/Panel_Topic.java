package com.aplp.client.panels.bodyPanels;

import java.util.HashMap;
import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.widgets.Widget_Message;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Topic;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
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

	public static final String ARG_TOPIC = "topic";

	private Panel _mainPanel;
	private Panel _topicPanel;
	private Context _context;
	private Category _currentCategory;

	@Override
	public Widget getWidget(Context context) {
		if(this._mainPanel == null) {
			this._context = context;
			this.initializeWidget();
		}

		return this._mainPanel;
	}



	private void initializeWidget() {
		//Create the main panel
		this._mainPanel = new VerticalPanel();

		//Create the back link
		HTML backWidget = new HTML("<a href=\"javascript:undefined;\">Back</a>");
		backWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Category category = Panel_Topic.this._currentCategory;
				if(category == null) {
					Panel_Topic.this._context.switchCurrentPanel(PanelsEnum.PANEL_CATEGORY_LIST, null, null);
				} else {
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("category", category);
					Panel_Topic.this._context.switchCurrentPanel(PanelsEnum.PANEL_TOPIC_LIST, null, arguments);
				}
				
			}
		});
		this._mainPanel.add(backWidget);

		//Create the message panel
		this._topicPanel = new VerticalPanel();
		this._mainPanel.add(this._topicPanel);
	}



	@Override
	public void onSetVisible(Map<String, Object> arguments) {
		//Clear the panel
		this._topicPanel.clear();

		try {
			//Get the message object
			Topic topic = this.getTopic(arguments);
			
			//Register the category
			this.registerCategory(topic);
			
			//Create the message widget
			Widget messageWidget = new Widget_Message(this._context, topic);

			//Add the widget
			this._topicPanel.add(messageWidget);
		} catch (Exception e) {
			Label errorMessage = new Label(e.getMessage());
			this._topicPanel.add(errorMessage);
		}


	}
	
	

	/**
	 * Get the message argument. And test its correctness
	 * @param arguments
	 * @return The message object
	 * @throws Exception
	 */
	private Topic getTopic(Map<String, Object> arguments) throws Exception {
		if(!arguments.containsKey(ARG_TOPIC)) {
			throw new Exception("No topic has been selected");
		}
		if(!(arguments.get(ARG_TOPIC) instanceof Topic)) {
			throw new Exception("The topic type is \"" + arguments.get(ARG_TOPIC).getClass().getName() + "\" instead of \"" + Topic.class.getName() + "\"");
		}

		//Get the message object
		Topic topic = (Topic) arguments.get(ARG_TOPIC);
		
		return topic;
	}
	
	
	
	/**
	 * Find and register the category object in 
	 * @param message
	 */
	private void registerCategory(Topic topic) {
		this._currentCategory = null;
		this._context.getForumService().getCategory_byId(topic.get_categoryId(), new AsyncCallback<Category>() {
			
			@Override
			public void onSuccess(Category result) {
				Panel_Topic.this._currentCategory = result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Panel_Topic.this._currentCategory = null;
			}
		});
		
	}



	@Override
	public void onSetInvisible(Map<String, Object> arguments) {
		this._currentCategory = null;
		this._topicPanel.clear();
	}



	@Override
	public void onPanelChange(PanelsEnum oldPanel,
			Map<String, Object> oldPanelArguments, PanelsEnum newPanel,
			Map<String, Object> newPanelArguments) {

	}
}
