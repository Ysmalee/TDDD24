package com.aplp.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aplp.server.database.Database;
import com.aplp.shared.businessObjects.User;

public class DAO_User extends DAO {
	

	
	/**
	 * Create a new User manager object
	 * @param database Database object
	 * @throws SQLException 
	 */
	public DAO_User(Database database) {
		super(database);
		
	}
	
	

	@Override
	protected void createTableIfNotExists() throws SQLException {
		Statement stat = this.get_database().getConnection().createStatement();

		String req = "" +
				"CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME + " (" +
				"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				"login VARCHAR(40) NOT NULL, " +
				"password VARCHAR(40) NOT NULL, " +
				"moderator BOOLEAN NOT NULL, " +
				"UNIQUE(login) " +
				");";

		stat.executeUpdate(req);
	}
	
	
	
	
	
	/**
	 * Search for the user from his ID
	 * @param id User id
	 * @return The user object or null if no user corresponding to the ID
	 * @throws SQLException
	 */
	public User getUser_byId(Integer id) throws SQLException {
		if(id == null) {
			throw new IllegalArgumentException("The \"id\" argument must not be null");
		}

		//Prepare the request
		String sql = "SELECT login, password, moderator FROM " + User.TABLE_NAME + " WHERE id = ?";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setInt(1,  id);

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		if(! result.next()) {
			return null;
		}

		//Create the user object
		String login = result.getString(1);
		String password = result.getString(2);
		Boolean moderator = result.getBoolean(3);
		User user = new User(id, login, password, moderator);
		
		return user;
	}
	
	
	
	
	/**
	 * Search for the user from his login
	 * @param login User login
	 * @return The user object or null if no user corresponding to the login
	 * @throws SQLException
	 */
	public User getUser_byLogin(String login) throws SQLException {
		if(login == null) {
			throw new IllegalArgumentException("The \"login\" argument must not be null");
		}

		//Prepare the request
		String sql = "SELECT id, password, moderator FROM " + User.TABLE_NAME + " WHERE login = ?";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setString(1,  login);

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		if(! result.next()) {
			return null;
		}

		//Create the user object
		Integer id = result.getInt(1);
		String password = result.getString(2);
		Boolean moderator = result.getBoolean(3);
		User user = new User(id, login, password, moderator);
		
		
		return user;
	}
 
}
