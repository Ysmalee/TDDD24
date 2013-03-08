package com.aplp.client.panels.bodyPanels;

import java.util.List;
import java.util.Map;
import com.aplp.shared.businessObjects.*;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.client.widgets.Widget_Category;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Panel_CategoryList implements BodyPanel {
	
	private Panel _panel_main;
	private Context _context;
	
	private Label _labelError;
	
	//###############################################################
	// Singleton pattern
	//###############################################################
	
	private static Panel_CategoryList instance;

	public static Panel_CategoryList getInstance() {
		synchronized(Panel_CategoryList.class) {
			if (instance == null) {
				instance = new Panel_CategoryList();
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
		//Create the main panel
		this._panel_main = new VerticalPanel();
		//Create the error label
		this._labelError = new Label("");
		this._labelError.setVisible(false);

		
		this._context.getForumService().getCategories(new AsyncCallback<List<Category>>() {
			
			@Override
			public void onSuccess(List<Category> result) {
				if (result.isEmpty()) {
					Label labelEmpty = new Label("There is no category right now");
					Panel_CategoryList.this._panel_main.add(labelEmpty);
				} else {
					for(Category c : result) {
						Panel_CategoryList.this._panel_main.add(new Widget_Category(Panel_CategoryList.this._context, c));
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Panel_CategoryList.this._labelError.setText(caught.getMessage());
				Panel_CategoryList.this._labelError.setVisible(true);				
			}
		});
		
	}


	/**
	 * Reset the fields text/visibility
	 */
	private void clearWidgets() {
		Panel_CategoryList.this._labelError.setText("");
		Panel_CategoryList.this._labelError.setVisible(false);
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
