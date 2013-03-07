package com.aplp.client.services;

import java.util.List;

import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Service_ForumAsync {

	void getAnswers(Message message, AsyncCallback<List<Answer>> callback);

	void getOwner(Message message, AsyncCallback<User> callback);

	void getTopics(Category category, AsyncCallback<List<Topic>> callback);

	void getCategories(AsyncCallback<List<Category>> callback);

	void createTopic(Topic topic, AsyncCallback<Topic> callback);

	void createAnswer(Answer answer, AsyncCallback<Answer> callback);

	void getCategory_byId(Integer id, AsyncCallback<Category> callback);

	void removeMessage(Message message, AsyncCallback<Void> callback);

}
