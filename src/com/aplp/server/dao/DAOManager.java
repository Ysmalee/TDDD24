package com.aplp.server.dao;

import java.sql.SQLException;

import com.aplp.server.database.Database;
import com.aplp.server.database.SQLiteDatabase;

public class DAOManager {
	
	//########################################################
	// Singleton
	//########################################################	
	
	private static final String DATABASE_LOCATION = "database.sqlite";
	
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
	
	private DAOManager(Database database) throws SQLException {
		this._database = database;
		
		//Create the DAO
		this._daoUser = new DAO_User(this._database);
		
		//Check and initialize the tables
		this.createTableIfNotExists();
	}
	
	
	private void createTableIfNotExists() throws SQLException {
		this._daoUser.createTableIfNotExists();
	}
	
	
	public DAO_User getDAO_User() {
		return this._daoUser;
	}
}
