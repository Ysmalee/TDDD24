package com.aplp.server.database;

import java.sql.Connection;

public interface Database {
	
	/**
	 * Return the connection object
	 * @return
	 */
	Connection getConnection();
	
}
