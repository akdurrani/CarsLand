package com.carsland.carslandrest.service;

import com.carsland.carslandrest.db.DatabaseConnector;
import com.carsland.carslandrest.db.DatabaseUtils;
import com.carsland.carslandrest.objects.Receipt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
	private final static String ALL_RECEIPTS_QUERY = "SELECT * FROM receipts";
	
	 public static Receipt getReceiptById(int receiptid) {
	        //Get a new connection object before going forward with the JDBC invocation.
	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_RECEIPTS_QUERY + " WHERE receipts.receiptid = " + receiptid);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Receipt receipt = new Receipt();


	                    receipt.setOrderId(resultSet.getInt("orderid"));
	                    receipt.setReceiptId(resultSet.getInt("receiptid"));
	                    receipt.setCarId(resultSet.getInt("carid"));
	                    receipt.setModelId(resultSet.getString("modelid"));
	                    receipt.setQuantity(resultSet.getInt("quantity"));

	                    return receipt;

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
	 
	 public static List<Receipt> getAllReceipts(int orderid) {
	        List<Receipt> receipts = new ArrayList<Receipt>();

	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_RECEIPTS_QUERY + " WHERE receipts.order = " + orderid);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Receipt receipt = new Receipt();

	                    receipt.setOrderId(resultSet.getInt("orderid"));
	                    receipt.setReceiptId(resultSet.getInt("receiptid"));
	                    receipt.setCarId(resultSet.getInt("carid"));
	                    receipt.setModelId(resultSet.getString("modelid"));
	                    receipt.setQuantity(resultSet.getInt("quantity"));

	                    receipts.add(receipt);

	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return receipts;
	    }
	 
	 public static boolean AddReceipt(Receipt receipt) {

	        String sql = "INSERT INTO receipts  ( orderid, carid, modelid, quantity)" +
	                "VALUES (?, ?, ?, ?)";
	        Connection connection = DatabaseConnector.getConnection();
	        return DatabaseUtils.performReceiptDBUpdate(connection, sql, receipt.getOrderId(), receipt.getCarId(), receipt.getModelId(), receipt.getQuanity());

	    }
	
	
}
