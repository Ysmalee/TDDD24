package com.aplp.shared.businessObjects;

import java.util.Date;

public class Topic extends Message {
	
	private static final long serialVersionUID = -7711175003915317618L;

	public static final String TABLE_NAME = "topic";
	
	private Integer _categoryId;
	
	
	public Topic() {
		this._categoryId = null;
	}

	public Topic(Integer id, String title, String text, Date creationDate, Integer ownerId, Integer categoryId) {
		super(id, title, text, creationDate, ownerId);
		this.set_categoryId(categoryId);
	}
	
	public Topic(String title, String text, Date creationDate, Integer ownerId, Integer categoryId) {
		super(title, text, creationDate, ownerId);
		this.set_categoryId(categoryId);
	}

	
	public Integer get_categoryId() {
		return _categoryId;
	}
	public void set_categoryId(Integer _categoryId) {
		this._categoryId = _categoryId;
	}
}
