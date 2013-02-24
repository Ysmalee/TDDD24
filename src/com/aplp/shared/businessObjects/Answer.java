package com.aplp.shared.businessObjects;

import java.util.Date;

public class Answer extends Message {
	private static final long serialVersionUID = 6160444405072755646L;
	private Integer _parentMessageId;
	
	
	public Answer() {
		this._parentMessageId = null;
	}

	public Answer(Integer id, String title, String text, Date creationDate, Integer ownerId, Integer parentMessageId) {
		super(id, title, text, creationDate, ownerId);
		this.set_parentMessageId(parentMessageId);
	}
	public Answer(String title, String text, Date creationDate, Integer ownerId, Integer parentMessageId) {
		super(title, text, creationDate, ownerId);
		this.set_parentMessageId(parentMessageId);
	}
	
	
	
	
	public Integer get_parentMessageId() {
		return _parentMessageId;
	}
	public void set_parentMessageId(Integer _parentMessageId) {
		this._parentMessageId = _parentMessageId;
	}

}
