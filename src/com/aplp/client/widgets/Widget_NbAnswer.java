package com.aplp.client.widgets;

import java.util.List;

import com.aplp.client.Context;
import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Topic;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_NbAnswer extends Composite {

	private Context _context;
	private Topic _topic;

	private Label _nbAnswers;
	
	
	public Widget_NbAnswer(Context context, Topic topic) {
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
		this._nbAnswers = new Label(); 
		
		//Create the panels
		this._context.getForumService().getAnswers(this._topic, new AsyncCallback<List<Answer>>() {
			@Override
			public void onSuccess(List<Answer> result) {
				Widget_NbAnswer.this._nbAnswers.setText(Integer.toString(result.size()));
			}
			@Override
			public void onFailure(Throwable caught) {
				Widget_NbAnswer.this._nbAnswers.setText("");
			}

		});
		
		//Create the composit structure
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(this._nbAnswers);
		
		return compositWidget;
	}

}
