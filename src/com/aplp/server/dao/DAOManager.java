package com.aplp.server.dao;

import java.sql.SQLException;

import com.aplp.server.database.Database;
import com.aplp.server.database.SQLiteDatabase;

public class DAOManager {
	
	//########################################################
	// Singleton
	//########################################################	
	
	private static final String DATABASE_LOCATION = "\\\\YSOS\\Ysos_Intern\\Tmp\\database.sqlite";
	
	private static DAOManager _instance;
	
	public static DAOManager getInstance() throws Exception {
		if(DAOManager._instance == null) {
			Database database = new SQLiteDatabase(DATABASE_LOCATION);
			DAOManager._instance = new DAOManager(database);
		}
		return DAOManager._instance;
	}
	

	//########################################################
	// Instance
	//########################################################
	
	private Database _database;
	private DAO_User _daoUser;
	private DAO_Category _daoCategory;
	private DAO_Topic _daoTopic;
	private DAO_Answer _daoAnswer;
	
	private DAOManager(Database database) throws SQLException {
		this._database = database;
		
		//Create the DAO
		this._daoUser = new DAO_User(this._database);
		this._daoCategory = new DAO_Category(this._database);
		this._daoTopic = new DAO_Topic(this._database);
		this._daoAnswer = new DAO_Answer(this._database);
		
		//Check and initialize the tables
		this.createTableIfNotExists();
	}
	
	
	private void createTableIfNotExists() throws SQLException {
		this._daoUser.createTableIfNotExists();
		this._daoCategory.createTableIfNotExists();
		this._daoTopic.createTableIfNotExists();
		this._daoAnswer.createTableIfNotExists();
	}
	
	
	public DAO_User getDAO_User() {
		return this._daoUser;
	}
	
	public DAO_Category getDAO_Category() {
		return this._daoCategory;
	}


	public DAO_Topic getDAO_Topic() {
		return this._daoTopic;
	}
	
	public DAO_Answer getDAO_Answer() {
		return this._daoAnswer;
	}
}
