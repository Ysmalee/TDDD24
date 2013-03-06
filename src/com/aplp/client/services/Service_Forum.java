package com.aplp.client.services;

import java.util.List;

import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("service_forum")
public interface Service_Forum extends RemoteService {

	List<Answer> getAnswers(Message message);
	List<Topic> getTopics(Category category);
	User getOwner(Message message);
	List<Category> getCategories();
	Topic createTopic(Topic topic);
	Answer createAnswer(Answer answer);
	
}
