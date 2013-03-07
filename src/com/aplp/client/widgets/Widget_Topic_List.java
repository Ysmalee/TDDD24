package com.aplp.client.widgets;

import java.util.List;

import com.aplp.client.Context;
import com.aplp.shared.businessObjects.Topic;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_Topic_List extends Composite {

	private Context _context;
	private List<Topic> _listTopics;
	
	
	public Widget_Topic_List(Context context, List<Topic> listTopics) {
		if(listTopics == null) {
			throw new IllegalArgumentException("The \"topic list\" argument must not be null");
		}
		if(context == null) {
			throw new IllegalArgumentException("The \"context\" argument must not be null");
		}
		
		this._context = context;
		this._listTopics = listTopics;
		
		//Initialize the widget
		Widget compositWidget = this.createWidget();
		this.initWidget(compositWidget); //All composites must call initWidget() in their constructors.
	}
	
	
	
	private Widget createWidget() {
		//Create the table
		FlexTable table = new FlexTable();
		table.setText(0, 0, "Title");
		table.setText(0, 1, "Author");
		table.setText(0, 2, "Answers");
		table.setText(0, 3, "Date");

		int i = 1;
		
		for (Topic t : this._listTopics) {
			//Title
			Widget_Topic title = new Widget_Topic(this._context, t);
			Widget_User user = new Widget_User(this._context, t.get_ownerId());
			Widget_NbAnswer nbAnswer = new Widget_NbAnswer(this._context, t);
			Label date = new Label(t.get_creationDate().toString());
			
			table.setWidget(i, 0, title);
			table.setWidget(i, 1, user);
			table.setWidget(i, 2, nbAnswer);
			table.setWidget(i, 3, date);
			
			i++;
		}
		
		//Create the composit structure
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(table);
		
		return compositWidget;
	}

}
