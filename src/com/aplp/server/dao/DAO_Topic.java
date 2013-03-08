package com.aplp.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aplp.server.database.Database;
import com.aplp.shared.businessObjects.Answer;
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
		String sql = "SELECT * FROM " + Topic.TABLE_NAME +" WHERE categoryID = ?";
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
			Date creationDate = new Date(result.getDate(4).getTime());
			Integer ownerID = result.getInt(5);
			Integer categoryID = result.getInt(5);

			listTopics.add(new Topic(id, title, message, creationDate, ownerID, categoryID));
		}
		
		return listTopics;
	}

	public Topic createTopic(Topic topic) throws SQLException {
		//Prepare the request
		String sql = "INSERT INTO " + Topic.TABLE_NAME + " VALUES (null, ?, ?, ?, ?, ?)";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, topic.get_title());
		stat.setString(2, topic.get_text());
		stat.setDate(3, new java.sql.Date(topic.get_creationDate().getTime()));
		stat.setInt(4, topic.get_ownerId());
		stat.setInt(5, topic.get_categoryId());
		//Execute the request
		stat.executeUpdate();
		
		// Get the ID
		ResultSet res = stat.getGeneratedKeys();
		
		return new Topic(res.getInt(1), topic.get_title(), topic.get_text(), topic.get_creationDate(), topic.get_ownerId(), topic.get_categoryId());
	}
	
	
	public void removeTopic(Topic topic) throws Exception {
		if(topic == null) {
			throw new IllegalArgumentException("The \"topic\" argument must not be null");
		}
		if(topic.get_id() == null) {
			throw new IllegalArgumentException("The \"topic\" object must not have a null id");
		}
		
		//Remove the children answers
		DAO_Answer daoAnswer = DAOManager.getInstance().getDAO_Answer();
		List<Answer> answers = daoAnswer.getAnswer_Topic(topic);
		for (Answer child : answers) {
			daoAnswer.removeAnswer(child);
		}
		//Prepare the request
		String sql = "DELETE FROM " + Topic.TABLE_NAME + 
					" WHERE id=?;";

		
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setInt(1, topic.get_id());
		
		//Execute the request
		stat.executeUpdate();
	}
	
	
	public void updateTopic(Topic topic) throws SQLException {
		if(topic == null) {
			throw new IllegalArgumentException("The \"topic\" argument must not be null");
		}
		if(topic.get_id() == null) {
			throw new IllegalArgumentException("The \"topic\" object must not have a null id");
		}
		
		//Prepare the request
		String sql = "UPDATE " + Topic.TABLE_NAME + 
					" SET title=?, message=?, creationDate=?, ownerID=?, categoryID=? " +
					" WHERE id=?;";

		
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setString(1, topic.get_title());
		stat.setString(2, topic.get_text());
		stat.setDate(3, new java.sql.Date(topic.get_creationDate().getTime()));
		stat.setInt(4, topic.get_ownerId());
		stat.setInt(5, topic.get_categoryId());
		stat.setInt(6, topic.get_id());
		
		//Execute the request
		stat.executeUpdate();
	}

}
