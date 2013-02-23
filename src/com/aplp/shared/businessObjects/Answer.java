package com.aplp.shared.businessObjects;

import java.util.Date;

public class Answer extends Message {

	public Answer(Integer id, String title, String text, Date creationDate) {
		super(id, title, text, creationDate);
	}
	public Answer(String title, String text, Date creationDate) {
		super(title, text, creationDate);
	}

}
