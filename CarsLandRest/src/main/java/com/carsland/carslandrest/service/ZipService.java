package com.carsland.carslandrest.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carsland.carslandrest.db.DatabaseConnector;
import com.carsland.carslandrest.db.DatabaseUtils;
import com.carsland.carslandrest.objects.Zip;

public class ZipService {
	private final static String ALL_ZIP_QUERY = "SELECT * FROM zip";
	
	 public static Zip getByZip(int zipcode) {
	        //Get a new connection object before going forward with the JDBC invocation.
	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ZIP_QUERY + " WHERE zip.zip = " + zipcode);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Zip zip = new Zip();


	                    zip.setZip(resultSet.getInt("zip"));
	                    zip.setCity(resultSet.getString("city"));
	                    zip.setState(resultSet.getString("state"));

	                    return zip;

	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                try {

	                    // We will always close the connection once we are done interacting with the Database.
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return null;


	    }
	 

}
