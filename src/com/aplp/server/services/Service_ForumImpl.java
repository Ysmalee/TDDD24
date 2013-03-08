package com.aplp.server.services;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.aplp.client.services.Service_Forum;
import com.aplp.server.dao.DAOManager;
import com.aplp.shared.businessObjects.Answer;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class Service_ForumImpl extends RemoteServiceServlet implements Service_Forum {
	private static final long serialVersionUID = 3970776885811819012L;
	
	private DAOManager _daoManager = null;
	
	@Override
	public void init() throws ServletException {
		try {
			this._daoManager = DAOManager.getInstance();
		} catch (Exception e) {
			throw new ServletException("Unable to create the DAOManager", e);
		}
	}
	
	
	
	/**************************************CATEGORY***********************************/
	
	
	@Override
	public List<Category> getCategories() throws Exception {
		//Find the categories
		List<Category> listCategories;
		try {
			listCategories = this._daoManager.getDAO_Category().getCategories();
			return listCategories;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	@Override
	public Category getCategory_byId(Integer id) throws Exception {
		Category category;
		
		try {
			category = this._daoManager.getDAO_Category().getCategory_byId(id);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
		return category;
	}
	/********************************************************************************/


	
	
	/**************************************TOPIC***********************************/
	

	@Override
	public List<Topic> getTopics(Category category) throws Exception {
		//Find the categories
		List<Topic> listTopics;
		try {
			listTopics = this._daoManager.getDAO_Topic().getTopics(category);
			return listTopics;
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public Topic createTopic(Topic topic) throws Exception {
		try {
			return this._daoManager.getDAO_Topic().createTopic(topic);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	/********************************************************************************/

	
	
	
	
	/**************************************ANSWER***********************************/

		@Override
	public Answer createAnswer(Answer answer) throws Exception {
		try {
			return this._daoManager.getDAO_Answer().createAnswer(answer);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<Answer> getAnswers(Message message) throws Exception {
		List<Answer> answers;
		try {
			answers = this._daoManager.getDAO_Answer().getAnswers(message);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
		return answers;
	}

	/********************************************************************************/
	
	
	
	
	
	
	@Override
	public User getOwner(Message message) throws Exception {
		User user;
		try {
			user = this._daoManager.getDAO_User().getUser_byId(message.get_ownerId());
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return user;
	}

	@Override
	public void removeMessage(Message message) throws Exception {
		if(message == null) {
			throw new IllegalArgumentException("The \"message\" argument exception");
		}
		
		try {
			if(message instanceof Topic) {
				this._daoManager.getDAO_Topic().removeTopic((Topic) message);
			} else if(message instanceof Answer) {
				this._daoManager.getDAO_Answer().removeAnswer((Answer) message);
			} else {
				throw new IllegalArgumentException("The message type is not handled");
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void editMessage(Message message) throws Exception {
		if(message == null) {
			throw new IllegalArgumentException("The \"message\" argument exception");
		}
		
		try {
			if(message instanceof Topic) {
				this._daoManager.getDAO_Topic().updateTopic((Topic) message);
			} else if(message instanceof Answer) {
				this._daoManager.getDAO_Answer().updateAnswer((Answer) message);
			} else {
				throw new IllegalArgumentException("The message type is not handled");
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	


}
