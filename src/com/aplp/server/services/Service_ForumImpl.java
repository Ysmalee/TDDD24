package com.aplp.server.services;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<Category> getCategories() {
		//Find the categories
		List<Category> listCategories;
		try {
			listCategories = this._daoManager.getDAO_Category().getCategories();
			return listCategories;
		} catch (SQLException e) {
			//TODO: Add the exception handling
			return new ArrayList<Category>();
		}
	}
	
	
	@Override
	public Category getCategory_byId(Integer id) {
		Category category;
		
		try {
			category = this._daoManager.getDAO_Category().getCategory_byId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			category = null;
		}
		
		return category;
	}
	/********************************************************************************/


	
	
	/**************************************TOPIC***********************************/

	@Override
	public List<Topic> getTopics(Category category) {
		//Find the categories
		List<Topic> listTopics;
		try {
			listTopics = this._daoManager.getDAO_Topic().getTopics(category);
			return listTopics;
		} catch (SQLException e) {
			//TODO: Add the exception handling
			return new ArrayList<Topic>();
		}
	}
	
	@Override
	public Topic createTopic(Topic topic) {
		try {
			return this._daoManager.getDAO_Topic().createTopic(topic);
		} catch (SQLException e) {
			return null;
		}
	}
	/********************************************************************************/

	
	/**************************************ANSWER***********************************/

		@Override
	public Answer createAnswer(Answer answer) {
		try {
			return this._daoManager.getDAO_Answer().createAnswer(answer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Answer> getAnswers(Message message) {
		List<Answer> answers;
		try {
			answers = this._daoManager.getDAO_Answer().getAnswers(message);
		} catch (SQLException e) {
			answers = null;
			e.printStackTrace();
		}
		
		return answers;
	}

	/********************************************************************************/
	
	
	@Override
	public User getOwner(Message message) {
		User user;
		try {
			user = this._daoManager.getDAO_User().getUser_byId(message.get_ownerId());
		} catch (SQLException e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void removeMessage(Message message) {
		// TODO Auto-generated method stub
		System.err.println("TODO: Implement the removeMessage function");
	}

	


}
