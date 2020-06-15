package com.carsland.carslandrest.service;

import com.carsland.carslandrest.db.DatabaseConnector;
import com.carsland.carslandrest.db.DatabaseUtils;
import com.carsland.carslandrest.objects.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
	private final static String ALL_ORDERS_QUERY = "SELECT * FROM orders";
	
	 public static Order getOrderById(int id) {
	        //Get a new connection object before going forward with the JDBC invocation.
	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY + " WHERE orders.orderid = " + id);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Order order = new Order();


	                    order.setOrderId(resultSet.getInt("orderid"));
	                    order.setLname(resultSet.getString("last_name"));
	                    order.setFname(resultSet.getString("first_name"));
	                    order.setPhone(resultSet.getString("phone"));
	                    order.setEmail(resultSet.getString("email"));
	                    order.setDate(resultSet.getString("date"));
	                    order.setDelivery(resultSet.getString("delivery_method"));
	                    order.setAddr(resultSet.getString("address"));
	                    order.setCity(resultSet.getString("city"));
	                    order.setState(resultSet.getString("state"));
	                    order.setZip(resultSet.getString("zip"));
	                    order.setCountry(resultSet.getString("country"));
	                    order.setCardname(resultSet.getString("cardname"));
	                    order.setLastFour(resultSet.getString("last_four"));
	                    order.setTotal(resultSet.getFloat("total"));

	                    return order;

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
	 
	 public static Order getLastOrder() {
//		 	Order order = new Order();
		 	Connection connection = DatabaseConnector.getConnection();
	     	ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY + " order by orders.orderid desc limit 1");
	     	if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Order order = new Order();


	                    order.setOrderId(resultSet.getInt("orderid"));
	                    order.setLname(resultSet.getString("last_name"));
	                    order.setFname(resultSet.getString("first_name"));
	                    order.setPhone(resultSet.getString("phone"));
	                    order.setEmail(resultSet.getString("email"));
	                    order.setDate(resultSet.getString("date"));
	                    order.setDelivery(resultSet.getString("delivery_method"));
	                    order.setAddr(resultSet.getString("address"));
	                    order.setCity(resultSet.getString("city"));
	                    order.setState(resultSet.getString("state"));
	                    order.setZip(resultSet.getString("zip"));
	                    order.setCountry(resultSet.getString("country"));
	                    order.setCardname(resultSet.getString("cardname"));
	                    order.setLastFour(resultSet.getString("last_four"));
	                    order.setTotal(resultSet.getFloat("total"));

	                    return order;

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
	 
	 public static List<Order> getAllOrders() {
	        List<Order> orders = new ArrayList<Order>();

	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Order order = new Order();

	                    order.setOrderId(resultSet.getInt("orderid"));
	                    order.setLname(resultSet.getString("last_name"));
	                    order.setFname(resultSet.getString("first_name"));
	                    order.setPhone(resultSet.getString("phone"));
	                    order.setEmail(resultSet.getString("email"));
	                    order.setDate(resultSet.getString("date"));
	                    order.setDelivery(resultSet.getString("delivery_method"));
	                    order.setAddr(resultSet.getString("address"));
	                    order.setCity(resultSet.getString("city"));
	                    order.setState(resultSet.getString("state"));
	                    order.setZip(resultSet.getString("zip"));
	                    order.setCountry(resultSet.getString("country"));
	                    order.setCardname(resultSet.getString("cardname"));
	                    order.setLastFour(resultSet.getString("last_four"));
	                    order.setTotal(resultSet.getFloat("total"));

	                    orders.add(order);

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

	        return orders;
	    }
	 
	 
	 
	 	public static boolean AddOrder(Order order) {
	 		System.out.println("In add order");
	        String sql = "INSERT INTO orders  ( total, first_name, last_name, phone, email, date, delivery_method, address, city, state, zip, country, cardname, last_four)" +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        Connection connection = DatabaseConnector.getConnection();
	        System.out.println(order.getDate());
	        return DatabaseUtils.performOrderDBUpdate(connection, sql, order.getTotal(), order.getFname(), order.getLname(), order.getPhone(), order.getemail(), order.getDate(), order.getDelivery(), order.getAddr(), order.getCity(), order.getState(), order.getZip(), order.getCountry(), order.getCardname(), order.getLastFour());

	    }
	
	
}
