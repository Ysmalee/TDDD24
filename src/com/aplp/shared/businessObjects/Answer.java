package com.aplp.shared.businessObjects;

import java.util.Date;

public class Answer extends Message {
	private static final long serialVersionUID = 6160444405072755646L;
	
	public static final String TABLE_NAME = "Answer";

	private Integer _parentTopicId;
	private Integer _parentAnswerId;
	
	
	public Answer() {
		this.set_parentTopicId(null);
		this.set_parentAnswerId(null);
	}

	public Answer(Integer id, String title, String text, Date creationDate, Integer ownerId, Integer parentTopicId, Integer parentAnswerId) {
		super(id, title, text, creationDate, ownerId);
		this.set_parentTopicId(parentTopicId);
		this.set_parentAnswerId(parentAnswerId);
	}
	public Answer(String title, String text, Date creationDate, Integer ownerId, Integer parentTopicId, Integer parentAnswerId) {
		super(title, text, creationDate, ownerId);
		this.set_parentTopicId(parentTopicId);
		this.set_parentAnswerId(parentAnswerId);
	}

	
	
	
	public Integer get_parentTopicId() {
		return _parentTopicId;
	}

	public void set_parentTopicId(Integer _parentTopicId) {
		this._parentTopicId = _parentTopicId;
	}

	public Integer get_parentAnswerId() {
		return _parentAnswerId;
	}

	public void set_parentAnswerId(Integer _parentAnswerId) {
		this._parentAnswerId = _parentAnswerId;
	}
	
	
	
	


}
