package com.aplp.server.services;

import java.util.List;

import com.aplp.client.services.Service_Forum;
import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class Service_ForumImpl extends RemoteServiceServlet implements Service_Forum {
	private static final long serialVersionUID = 3970776885811819012L;

	@Override
	public List<Answer> getAnswers(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopics(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getOwner(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
