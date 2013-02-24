package com.aplp.server.dao;

import java.sql.SQLException;

import com.aplp.server.database.Database;



/**
 * Data access object
 * This kind of object is used to do the conversion between the business objects and the database
 * @author tim
 *
 */
public abstract class DAO {

	//###########################################################
	// DAO management 
	//###########################################################
	
	//TODO: Implement the DAO management
	
	
	

	//###########################################################
	// DAO subclassing
	//###########################################################
	
	/**
	 * Current database
	 */
	private Database _database;
	
	
	/**
	 * Create a new DAO object
	 * @param database Database linked to the DAO
	 * @throws SQLException 
	 */
	public DAO(Database database) throws SQLException {
		if(database == null) {
			throw new IllegalArgumentException("The \"database\" argument must not be null");
		}
		
		this._database = database;
		
		//Initialize the table
		this.createTableIfNotExists();
	}
	
	
	
	/**
	 * Called on the first object initialization
	 * Supposed to create the table related with the DAO 
	 * @throws SQLException 
	 */
	protected abstract void createTableIfNotExists() throws SQLException;

	
	
	/**
	 * Get the database object
	 * @return Database object
	 */
	public Database get_database() {
		return _database;
	}
	
}
