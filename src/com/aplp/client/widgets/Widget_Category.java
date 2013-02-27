package com.aplp.client.widgets;

import java.util.Map;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.shared.businessObjects.Category;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_Category extends Composite {

	private Context _context;
	private Category _category;
	
	
	public Widget_Category(Context context, Category category) {
		if(category == null) {
			throw new IllegalArgumentException("The \"category\" argument must not be null");
		}
		if(context == null) {
			throw new IllegalArgumentException("The \"context\" argument must not be null");
		}
		
		this._category = category;
		this._context = context;
		
		//Initialize the widget
		Widget compositWidget = this.createWidget();
		this.initWidget(compositWidget); //All composites must call initWidget() in their constructors.
	}
	
	
	
	private Widget createWidget() {
		//Create the panels
		Label title = new Label(this._category.get_name());
		title.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("category", Widget_Category.this._category);
				Widget_Category.this._context.switchCurrentPanel(PanelsEnum.PANEL_TOPIC_LIST, null, params);
			}
		});
		Label description = new Label(this._category.get_description());
		
		//Create the composit structure
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(title);
		compositWidget.add(description);
		
		return compositWidget;
	}

}
