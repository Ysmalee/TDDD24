package com.aplp.client.widgets;

import java.util.List;

import com.aplp.client.Context;
import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Message;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_Message extends Composite {
	
	private Message _message;
	private Context _context;
	
	
	
	public Widget_Message(Context context, Message message) {
		if(message == null) {
			throw new IllegalArgumentException("The \"message\" argument must not be null");
		}
		if(context == null) {
			throw new IllegalArgumentException("The \"context\" argument must not be null");
		}
		
		this._message = message;
		this._context = context;
		
		//Initialize the widget
		Widget compositWidget = this.createWidget();
		this.initWidget(compositWidget); //All composites must call initWidget() in their constructors.
	}
	
	
	
	private Widget createWidget() {
		//Create the panels
		Widget messageWidget = this.createMessageWidget();
		Panel answerPanel = this.createAnswerPanel();
		
		//Create the composit structure
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(messageWidget);
		compositWidget.add(answerPanel);
		
		return compositWidget;
	}
	
	
	
	private Widget createMessageWidget() {
		Label message = new Label();
		message.setText(this._message.get_text());
		
		return message;
	}
	
	
	
	private Panel createAnswerPanel() {
		//Create the parts of the answer panel
		Widget tabulation =  new Label("|");
		final VerticalPanel answers = new VerticalPanel();
		
		//Create the composit witget
		final HorizontalPanel tabulationPanel = new HorizontalPanel();
		tabulationPanel.add(tabulation);
		tabulationPanel.add(answers);
		
		//Fill the structure
		this._context.getForumService().getAnswers(this._message, new AsyncCallback<List<Answer>>() {
			@Override
			public void onFailure(Throwable caught) {
				answers.add(new Label(caught.getMessage()));
			}

			@Override
			public void onSuccess(List<Answer> result) {
				tabulationPanel.setVisible(result.size() != 0);
				for (Answer answer : result) {
					answers.add(new Widget_Message(Widget_Message.this._context, answer));
				}
			}
		});
		
		return tabulationPanel;
	}
	
	
	
}
