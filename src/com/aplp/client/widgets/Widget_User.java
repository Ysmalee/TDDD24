package com.aplp.client.widgets;

import com.aplp.client.Context;
import com.aplp.shared.businessObjects.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_User extends Composite {

	private Context _context;
	private Integer _userId;
	
	private Label _userLogin;
	
	
	public Widget_User(Context context, Integer userId) {
		if(userId == null) {
			throw new IllegalArgumentException("The \"user\" argument must not be null");
		}
		if(context == null) {
			throw new IllegalArgumentException("The \"context\" argument must not be null");
		}
		
		this._userId = userId;
		this._context = context;
		
		//Initialize the widget
		Widget compositWidget = this.createWidget();
		this.initWidget(compositWidget); //All composites must call initWidget() in their constructors.
	}
	
	
	
	private Widget createWidget() {
		this._userLogin = new Label("");
		
		//Create the panels
		this._context.getUserService().getUserById(this._userId, new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				Widget_User.this._userLogin.setText(result.get_login());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Widget_User.this._userLogin.setText("");
			}
		});
		
		VerticalPanel compositWidget = new VerticalPanel();
		compositWidget.add(this._userLogin);
		
		return compositWidget;
	}


}
