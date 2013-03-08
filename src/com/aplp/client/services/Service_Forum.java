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

	List<Answer> getAnswers(Message message) throws Exception;
	List<Topic> getTopics(Category category) throws Exception;
	User getOwner(Message message) throws Exception;
	Category getCategory_byId(Integer id) throws Exception;
	List<Category> getCategories() throws Exception;
	Topic createTopic(Topic topic) throws Exception;
	Answer createAnswer(Answer answer) throws Exception;
	void removeMessage(Message message) throws Exception;
	void editMessage(Message message) throws Exception;
	
}
