package com.aplp.shared.businessObjects;

import java.util.Date;

public class Topic extends Message {

	public Topic(Integer id, String title, String text, Date creationDate) {
		super(id, title, text, creationDate);
	}
	
	public Topic(String title, String text, Date creationDate) {
		super(title, text, creationDate);
	}

}
