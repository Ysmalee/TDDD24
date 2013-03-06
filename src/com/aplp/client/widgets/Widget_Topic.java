package com.aplp.client.widgets;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.panels.bodyPanels.Panel_Topic;
import com.aplp.shared.businessObjects.Topic;

import java.util.HashMap;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_Topic extends Composite {

	private Context _context;
	private Topic _topic;
	
	
	public Widget_Topic(Context context, Topic topic) {
		if(topic == null) {
			throw new IllegalArgumentException("The \"topic\" argument must not be null");
		}
		if(context == null) {
			throw new IllegalArgumentException("The \"context\" argument must not be null");
		}
		
		this._topic = topic;
		this._context = context;
		
		//Initialize the widget
		Widget compositWidget = this.createWidget();
		this.initWidget(compositWidget); //All composites must call initWidget() in their constructors.
	}
	
	
	
	private Widget createWidget() {
		//Create the panels
		HTML link = new HTML("<a href=\"javascript:undefined;\">" + this._topic.get_title() + "</a>");
		link.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(Panel_Topic.ARG_TOPIC, Widget_Topic.this._topic);
				Widget_Topic.this._context.switchCurrentPanel(PanelsEnum.PANEL_TOPIC, null, params);
			}
		});
		
		//Create the composit structure
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(link);
		
		return compositWidget;
	}

}
