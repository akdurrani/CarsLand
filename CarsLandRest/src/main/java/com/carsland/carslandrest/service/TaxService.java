package com.carsland.carslandrest.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carsland.carslandrest.db.DatabaseConnector;
import com.carsland.carslandrest.db.DatabaseUtils;
import com.carsland.carslandrest.objects.Tax;

public class TaxService {
	private final static String ALL_TAX_QUERY = "SELECT * FROM tax";
	
	 public static Tax getTaxByZip(int zip) {
	        //Get a new connection object before going forward with the JDBC invocation.
	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_TAX_QUERY + " WHERE tax.ZipCode = " + zip);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Tax tax = new Tax();


	                    tax.setZip(resultSet.getInt("ZipCode"));
	                    tax.setRate(resultSet.getDouble("CombinedRate"));

	                    return tax;

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
