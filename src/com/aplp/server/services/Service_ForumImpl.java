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
	public void createTopic(Topic topic) {
		this._daoManager.getDAO_Topic().createTopic(topic);
	}
	/********************************************************************************/

	
	
	@Override
	public List<Answer> getAnswers(Message message) {
		return null;
	}

	@Override
	public User getOwner(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
}
