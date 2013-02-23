package com.aplp.shared.businessObjects;

import java.util.Date;

public abstract class Message extends BusinessObject {
	
	private String _title;
	private String _text;
	private Date _creationDate;
	
	
	
	
	public Message(Integer id, String title, String text, Date creationDate) {
		super(id);
		this.set_title(title);
		this.set_text(text);
		this.set_creationDate(creationDate);
	}
	
	public Message(String title, String text, Date creationDate) {
		super();
		this.set_title(title);
		this.set_text(text);
		this.set_creationDate(creationDate);
	}

	
	
	

	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}

	public Date get_creationDate() {
		return _creationDate;
	}
	public void set_creationDate(Date _creationDate) {
		this._creationDate = _creationDate;
	}

	public String get_text() {
		return _text;
	}
	public void set_text(String _text) {
		this._text = _text;
	}
}
