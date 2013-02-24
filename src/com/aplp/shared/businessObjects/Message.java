package com.aplp.shared.businessObjects;

import java.util.Date;

public abstract class Message extends BusinessObject {
	
	private static final long serialVersionUID = 1335764898183410792L;
	
	private String _title;
	private String _text;
	private Date _creationDate;
	private Integer _ownerId;
	
	
	public Message() {
		this._title = null;
		this._text = null;
		this._creationDate = null;
		this._ownerId = null;
	}
	
	public Message(Integer id, String title, String text, Date creationDate, Integer ownerId) {
		super(id);
		this.set_title(title);
		this.set_text(text);
		this.set_creationDate(creationDate);
		this.set_ownerId(ownerId);
	}
	
	public Message(String title, String text, Date creationDate, Integer ownerId) {
		super();
		this.set_title(title);
		this.set_text(text);
		this.set_creationDate(creationDate);
		this.set_ownerId(ownerId);
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

	public Integer get_ownerId() {
		return _ownerId;
	}
	public void set_ownerId(Integer _ownerId) {
		this._ownerId = _ownerId;
	}
}
