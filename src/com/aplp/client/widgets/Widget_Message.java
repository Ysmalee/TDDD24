package com.aplp.client.widgets;

import java.util.Date;
import java.util.List;

import com.aplp.client.Context;
import com.aplp.client.panels.PanelsEnum;
import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Widget_Message extends Composite {

	private Panel _messagePanel = new VerticalPanel();
	private HTML _messageLabel = new HTML();
	private Panel _answersPanel = new VerticalPanel();
	private Panel _answerPanel = new VerticalPanel();
	private TextArea _answerTextArea = new TextArea();
	private Panel _editPanel = new VerticalPanel();
	private TextArea _editTextArea = new TextArea();
	private Panel _actionPanel = new VerticalPanel();

	private HTML _errorLabel = new HTML();

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
		//Error label
		this.hideErrorLabel();

		//Create the panels
		Panel titlePanel = this.createTitlePanel();
		Panel messagePanel = this.createMessagePanel();
		Panel answerPanel = this.createAnswerPanel();
		Panel editPanel = this.createEditPanel();
		Panel actionPanel = this.createActionPanel();
		Panel answersPanel = this.createAnswersPanel();
		Panel userPanel = this.createUserPanel();



		Panel postPanel = new VerticalPanel();
		postPanel.add(messagePanel);
		postPanel.add(editPanel);
		this.hideEditPanel();

		Panel titleMessageAnswerPanel = new VerticalPanel();
		titleMessageAnswerPanel.add(titlePanel);
		titleMessageAnswerPanel.add(postPanel);
		titleMessageAnswerPanel.add(answerPanel);
		this.hideAnswerPanel();

		Panel mainPanel = new HorizontalPanel();
		mainPanel.add(titleMessageAnswerPanel);
		mainPanel.add(actionPanel);



		//Add the mouse over handling
		mainPanel.addDomHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				Widget_Message.this._actionPanel.setVisible(true);
			}
		}, MouseOverEvent.getType());


		//Add the mouse out handling
		mainPanel.addDomHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				Widget_Message.this._actionPanel.setVisible(false);
			}
		}, MouseOutEvent.getType());



		//Create the messages structure
		HTMLTable structure = new Grid(3, 2);
		structure.setWidget(0, 1, this._errorLabel);
		structure.setWidget(1, 0, userPanel);
		structure.setWidget(1, 1, mainPanel);
		structure.setWidget(2, 1, answersPanel);

		return structure;
	}





	private Panel createUserPanel() {
		//Data
		final Label user = new Label("");
		final Image moderator = new Image("images/moderator.png");
		moderator.setVisible(false);
		final Image avatar = new Image("images/avatar_default.png");

		//Ask for the owner of the message
		this._context.getForumService().getOwner(this._message, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				Widget_Message.this.showErrorLabel(caught.getMessage());
			}
			@Override
			public void onSuccess(User result) {
				Widget_Message.this.hideErrorLabel();
				if(result != null) {
					user.setText(result.get_login());
					moderator.setVisible(result.get_moderator());
				}
			}
		});

		//Create the panels
		Panel userNamePanel = new HorizontalPanel();
		userNamePanel.add(user);
		userNamePanel.add(moderator);

		Panel userPanel = new VerticalPanel();
		userPanel.add(userNamePanel);
		userPanel.add(avatar);
		return userPanel;
	}


	private Panel createActionPanel() {
		//Answer image
		Image answerImage = new Image("images/answer.png");
		answerImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.showAnswerPanel();
			}
		});

		//Remove image
		Image removeImage = new Image("images/remove.gif");
		removeImage.setVisible(this._context.getUserConnected().get_moderator());
		removeImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.removeMessage();
			}
		});
		//Remove image
		Image editImage = new Image("images/edit.png");
		editImage.setVisible(this._context.getUserConnected().get_moderator());
		editImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.showEditPanel();
			}
		});

		this._actionPanel.add(answerImage);
		this._actionPanel.add(editImage);
		this._actionPanel.add(removeImage);
		this._actionPanel.setVisible(false);

		return this._actionPanel;
	}



	private Panel createEditPanel() {
		//Buttons
		Button editButton = new Button("Edit", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.editMessage(Widget_Message.this._editTextArea.getText());
			}
		});
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.hideEditPanel();
			}
		});

		//Button panel
		Panel buttonPanel = new HorizontalPanel();
		buttonPanel.add(editButton);
		buttonPanel.add(cancelButton);

		//Answer panel
		this._editPanel.add(this._editTextArea);
		this._editPanel.add(buttonPanel);

		return this._editPanel;
	}


	private Panel createTitlePanel() {
		//Data
		Label title = new Label(this._message.get_title());
		Label creationDate = new Label(this._message.get_creationDate().toString());

		//Title panel
		Panel titlePanel = new HorizontalPanel();
		titlePanel.add(title);
		titlePanel.add(creationDate);

		return titlePanel;
	}



	private Panel createMessagePanel() {
		this._messageLabel.setText(this._message.get_htmlText());

		//Text panel
		this._messagePanel.add(this._messageLabel);

		return this._messagePanel;
	}



	private Panel createAnswerPanel() {
		//Buttons
		Button sendButton = new Button("Send", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.sendAnswer(Widget_Message.this._answerTextArea.getText());
			}
		});
		Button cancelButton = new Button("Cancel", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Widget_Message.this.hideAnswerPanel();
			}
		});

		//Button panel
		Panel buttonPanel = new HorizontalPanel();
		buttonPanel.add(sendButton);
		buttonPanel.add(cancelButton);

		//Answer panel
		this._answerPanel.add(this._answerTextArea);
		this._answerPanel.add(buttonPanel);

		return this._answerPanel;
	}



	/**
	 * Creates a panel containing all the answers
	 * @return
	 */
	private Panel createAnswersPanel() {
		//Fill the structure
		this._context.getForumService().getAnswers(this._message, new AsyncCallback<List<Answer>>() {
			@Override
			public void onFailure(Throwable caught) {
				Widget_Message.this._answersPanel.add(new Label(caught.getMessage()));
			}

			@Override
			public void onSuccess(List<Answer> result) {
				for (Answer answer : result) {
					Widget_Message.this.addAnswer(answer);
				}
			}
		});

		return this._answersPanel;
	}



	/**
	 * Add an answer the the answer list (Create a new Widget_Message)
	 * @param answer new Answer
	 */
	private void addAnswer(Answer answer) {
		this._answersPanel.add(new Widget_Message(this._context, answer));
	}



	/**
	 * Show the answer panel and the buttons
	 */
	private void showAnswerPanel() {
		this._answerTextArea.setText("");
		this._answerPanel.setVisible(true);
	}



	/**
	 * Hide the answer panel
	 */
	private void hideAnswerPanel() {
		this._answerPanel.setVisible(false);
		this._answerTextArea.setText("");
	}


	/**
	 * Show the answer panel and the buttons
	 */
	private void showEditPanel() {
		this._editTextArea.setText(this._message.get_text());
		this._messagePanel.setVisible(false);
		this._editPanel.setVisible(true);
	}



	/**
	 * Hide the answer panel
	 */
	private void hideEditPanel() {
		this._editTextArea.setText(this._message.get_text());
		this._editPanel.setVisible(false);
		this._messagePanel.setVisible(true);
	}



	/**
	 * Create a new answer record in database and add a widget on success
	 * @param message new answer
	 */
	private void sendAnswer(String message) {
		if(message.isEmpty()) {
			return;
		}

		//Create the answer object
		Answer answer;
		if(this._message instanceof Topic) {
			answer = new Answer("", message, new Date(), this._context.getUserConnected().get_id(), this._message.get_id(), null);
		} else if(this._message instanceof Answer) {
			answer = new Answer("", message, new Date(), this._context.getUserConnected().get_id(), null, this._message.get_id());
		} else {
			throw new IllegalStateException("The \"message\" attribute class is unknown");
		}

		//Send the object
		this._context.getForumService().createAnswer(answer, new AsyncCallback<Answer>() {
			@Override
			public void onFailure(Throwable caught) {
				Widget_Message.this.showErrorLabel(caught.getMessage());
			}

			@Override
			public void onSuccess(Answer result) {
				Widget_Message.this.hideErrorLabel();
				Widget_Message.this.hideAnswerPanel();
				Widget_Message.this.addAnswer(result);
			}
		});
	}




	/**
	 * Edit the current message
	 * @param newText New text
	 */
	private void editMessage(final String newText) {
		this._message.set_text(newText);
		
		this._context.getForumService().editMessage(this._message, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Widget_Message.this.showErrorLabel(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Widget_Message.this.hideErrorLabel();
				Widget_Message.this.hideEditPanel();
				Widget_Message.this._messageLabel.setText(Widget_Message.this._message.get_htmlText());
			}
		});
	}



	/**
	 * Show the error label with the message given
	 * @param message Error message
	 */
	private void showErrorLabel(String message) {
		Widget_Message.this._errorLabel.setText(message);
		Widget_Message.this._errorLabel.setVisible(true);
	}



	/**
	 * Hide the error label
	 */
	private void hideErrorLabel() {
		Widget_Message.this._errorLabel.setText("");
		Widget_Message.this._errorLabel.setVisible(false);
	}



	/**
	 * Remove the current message
	 */
	private void removeMessage() {
		//Send a message to remove the message and all the children
		this._context.getForumService().removeMessage(this._message, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Widget_Message.this.showErrorLabel(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				if(Widget_Message.this._message instanceof Topic) {
					//If the topic is distroyed, leave this interface
					Widget_Message.this._context.switchCurrentPanel(PanelsEnum.PANEL_CATEGORY_LIST, null, null);
				} else {
					Widget_Message.this.hideErrorLabel();

					//Remove the children widgets
					Widget_Message.this._answersPanel.clear();
					Widget_Message.this.setVisible(false);
				}
			}
		});
	}



}
