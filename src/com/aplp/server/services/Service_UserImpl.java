package com.aplp.server.services;

import java.sql.SQLException;

import javax.servlet.ServletException;

import com.aplp.client.services.Service_User;
import com.aplp.server.dao.DAOManager;
import com.aplp.shared.businessObjects.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class Service_UserImpl extends RemoteServiceServlet implements Service_User {
	private static final long serialVersionUID = -8106823898790043925L;

	private DAOManager _daoManager = null;
	
	@Override
	public void init() throws ServletException {
		try {
			this._daoManager = DAOManager.getInstance();
		} catch (Exception e) {
			throw new ServletException("Unable to create the DAOManager", e);
		}
	}
	
	@Override
	public User login(String login, String password) {
		//Find the user
		User user;
		try {
			user = this._daoManager.getDAO_User().getUser_byLogin(login);
		} catch (SQLException e) {
			//TODO: Add the exception handling
			return null;
		}
		
		//Test the password
		if(user != null && user.get_password().equals(password)) {
			return user;
		} else {
			return null;
		}
	}
	
	public User getUserById(Integer id) {
		//Find the user
		User user;
		try {
			user = this._daoManager.getDAO_User().getUser_byId(id);
			return user;
		} catch (SQLException e) {
			//TODO: Add the exception handling
			return null;
		}
	}


}
