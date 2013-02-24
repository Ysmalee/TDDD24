package com.aplp.server.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteDatabase implements Database {
	/**
	 * Connection object to sqlite database
	 */
	private Connection _connection;



	/**
	 * Create a new SQL database object
	 * @param databaseLocation Database location
	 * @throws Exception
	 */
	public SQLiteDatabase(String databaseLocation) throws Exception {
		//Test the JDBC driver
		try {
			Class.forName("SQLite.JDBCDriver");
		} catch(ClassNotFoundException e) {
			throw new Exception("SQLite driver not found", e);
		}
		

		//Try to connect to the database (May also create the DB)
		this._connection = DriverManager.getConnection("jdbc:sqlite:" + databaseLocation);

	}




	@Override
	public Connection getConnection() {
		return this._connection;
	}


}
