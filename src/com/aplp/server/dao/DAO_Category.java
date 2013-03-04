package com.aplp.server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aplp.server.database.Database;
import com.aplp.shared.businessObjects.Category;

public class DAO_Category extends DAO {

	public DAO_Category(Database database) {
		super(database);
	}

	@Override
	protected void createTableIfNotExists() throws SQLException {
		Statement stat = this.get_database().getConnection().createStatement();

		String req = "" +
				"CREATE TABLE IF NOT EXISTS " + Category.TABLE_NAME + " (" +
				"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				"name VARCHAR(20) NOT NULL, " +
				"descritpion VARCHAR(150) NOT NULL, " +
				"UNIQUE(name) " +
				");";

		stat.executeUpdate(req);
		
		insertTestCategories();
	}
	
	public void insertTestCategories() throws SQLException {
		//Tests
	}

	public List<Category> getCategories() throws SQLException{
		List<Category> listCategories = new ArrayList<Category>();

		//Prepare the request
		String sql = "SELECT * FROM " + Category.TABLE_NAME;
		PreparedStatement stat = this.get_database().getConnection().prepareStatement(sql);

		//Execute the request
		ResultSet result = stat.executeQuery();

		//Test if there is a result
		while(result.next()) {
			//Create the category object
			Integer id = result.getInt(1);
			String name = result.getString(2);
			String description = result.getString(3);
			listCategories.add(new Category(id, name, description));
		}
		
		return listCategories;
	}

}
