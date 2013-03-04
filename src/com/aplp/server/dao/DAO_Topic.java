package com.aplp.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aplp.server.database.Database;
import com.aplp.shared.businessObjects.Category;
import com.aplp.shared.businessObjects.Topic;

public class DAO_Topic extends DAO {

	public DAO_Topic(Database database) {
		super(database);
	}

	@Override
	protected void createTableIfNotExists() throws SQLException {
		Statement stat = this.get_database().getConnection().createStatement();

		String req = "" +
				"CREATE TABLE IF NOT EXISTS " + Topic.TABLE_NAME + " (" +
				"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				"title VARCHAR(20) NOT NULL, " +
				"message TEXT NOT NULL, " +
				"creationDate DATE NOT NULL, " +
				"ownerID INTEGER NOT NULL, " +
				"categoryID INTEGER NOT NULL " +
				");";

		stat.executeUpdate(req);
		
		insertTestTopics();
	}
	
	public void insertTestTopics() throws SQLException {
		//Tests
	}

	public List<Topic> getTopics(Category category) throws SQLException{
		List<Topic> listTopics = new ArrayList<Topic>();

		//Prepare the request
		String sql = "SELECT * FROM " + Topic.TABLE_NAME +"WHERE categoryID = ?";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		
		stat.setInt(1,  category.get_id());

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		while(result.next()) {
			//Create the topic object
			Integer id = result.getInt(1);
			String title = result.getString(2);
			String message = result.getString(3);
			Date creationDate = result.getDate(4);
			Integer ownerID = result.getInt(5);
			Integer categoryID = result.getInt(5);

			listTopics.add(new Topic(id, title, message, creationDate, ownerID, categoryID));
		}
		
		return listTopics;
	}

}
