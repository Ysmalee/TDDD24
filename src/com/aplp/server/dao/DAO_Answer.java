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
import com.aplp.shared.businessObjects.Message;
import com.aplp.shared.businessObjects.Topic;
import com.aplp.shared.businessObjects.User;

public class DAO_Answer extends DAO {

	public DAO_Answer(Database database) {
		super(database);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createTableIfNotExists() throws SQLException {
		Statement stat = this.get_database().getConnection().createStatement();

		String req = "" +
				"CREATE TABLE IF NOT EXISTS " + Answer.TABLE_NAME + " (" +
				"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				"title VARCHAR(20) NOT NULL, " +
				"message TEXT NOT NULL, " +
				"creationDate DATE NOT NULL, " +
				"ownerID INTEGER NOT NULL, " +
				"answerID INTEGER, " +
				"topicID INTEGER" +
				");";

		stat.executeUpdate(req);
	}






	public Answer createAnswer(Answer answer) throws SQLException {
		if(answer == null) {
			throw new IllegalArgumentException("The \"answer\" argument must not be null");
		}
		if(answer.get_id() != null) {
			throw new IllegalArgumentException("The \"answer\" object must have a null id");
		}
		
		//Prepare the request
		String sql = "INSERT INTO table_name (title, message, creationDate, ownerID, answerID, topicID)" +
				"VALUES (?, ?, ?, ?, ?, ?)";
		Statement stat = this.get_database().getConnection().createStatement();
	}




	/**
	 * Return the answers of a message
	 * @param message Message object
	 * @return Answer list (may be empty list)
	 * @throws SQLException 
	 */
	public List<Answer> getAnswers(Message message) throws SQLException {
		if(message instanceof Topic) {
			return this.getAnswer_Topic((Topic) message);
		} else if(message instanceof Answer) {
			return this.getAnswer_Answer((Answer) message);
		} else {
			throw new IllegalArgumentException("The \"message\" argument object type is unknown");
		}
	}


	/**
	 * Return all the answers of an answer object
	 * @param answer Reference object 
	 * @return Answers list
	 * @throws SQLException 
	 */
	private List<Answer> getAnswer_Answer(Answer answer) throws SQLException {
		if(answer == null) {
			throw new IllegalArgumentException("The \"answer\" argument must not be null");
		}
		if(answer.get_id() == null) {
			throw new IllegalArgumentException("The \"answer\" object must have a valid id");
		}


		//Prepare the request
		String sql = "SELECT id, title, message, creationDate, ownerID, answerID, topicID FROM " + Answer	.TABLE_NAME + " WHERE answerID = ?";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setInt(1,  answer.get_id());

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		List<Answer> answers = new ArrayList<Answer>();
		while(result.next()) {
			//Create the answer
			Integer id = result.getInt(1);
			String title = result.getString(2);
			String message = result.getString(3);
			Date creationDate = new Date(result.getDate(4).getTime());
			Integer ownerId = result.getInt(5);
			Integer answerId = result.getInt(6);
			Integer topicId = result.getInt(7);
			Answer newAnswer = new Answer(id, title, message, creationDate, ownerId, topicId, answerId);

			//Add it to the list
			answers.add(newAnswer);
		}

		return answers;
	}


	/**
	 * Return all the answers of an topic object
	 * @param answer Reference object 
	 * @return Answers list
	 * @throws SQLException 
	 */
	private List<Answer> getAnswer_Topic(Topic topic) throws SQLException {
		if(topic == null) {
			throw new IllegalArgumentException("The \"topic\" argument must not be null");
		}
		if(topic.get_id() == null) {
			throw new IllegalArgumentException("The \"topic\" object must have a valid id");
		}


		//Prepare the request
		String sql = "SELECT id, title, message, creationDate, ownerID, answerID, topicID FROM " + Answer	.TABLE_NAME + " WHERE topicID = ?";
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);
		stat.setInt(1,  topic.get_id());

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		List<Answer> answers = new ArrayList<Answer>();
		while(result.next()) {
			//Create the answer
			Integer id = result.getInt(1);
			String title = result.getString(2);
			String message = result.getString(3);
			Date creationDate = new Date(result.getDate(4).getTime());
			Integer ownerId = result.getInt(5);
			Integer answerId = result.getInt(6);
			Integer topicId = result.getInt(7);
			Answer newAnswer = new Answer(id, title, message, creationDate, ownerId, topicId, answerId);

			//Add it to the list
			answers.add(newAnswer);
		}

		return answers;
	}

}
