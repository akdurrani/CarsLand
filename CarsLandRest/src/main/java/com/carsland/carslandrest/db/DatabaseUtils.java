package com.carsland.carslandrest.db;


import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class DatabaseUtils {


    public static ResultSet retrieveQueryResults(Connection connection, final String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public static ResultSet retrieveQueryResultsWithParam(Connection connection, final String sql, Object param) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) param);
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;


    }

    public static boolean performOrderDBUpdate(Connection connection, String sql, float total, 
    		String first_name, String last_name, String phone, String email, 
    		String date, String delivery_method, String address, String city, 
    		String state, String zip, String country, String cardname, 
    		String last_four) {
        PreparedStatement preparedStatement = null;
        System.out.println("In order db update before try");
        try {
        	System.out.println("In order db update before");
            preparedStatement = connection.prepareStatement(sql);
            System.out.println("In order db update after");
            LocalDateTime time = LocalDateTime.now();
            ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
            ZonedDateTime zdt = time.atZone(zoneId);
            long epoch = zdt.toInstant().toEpochMilli();
//            long epoch = time.atZone(zoneId).toEpochSecond();
            int i = 1;
//            preparedStatement.setInt(i++, orderid);
            System.out.println(total);
            preparedStatement.setFloat(i++, total);
            System.out.println(first_name);
            preparedStatement.setString(i++, first_name);
            System.out.println(last_name);
            preparedStatement.setString(i++, last_name);
            System.out.println(phone);
            preparedStatement.setString(i++, phone);
            System.out.println(email);
            preparedStatement.setString(i++, email);
            System.out.println(date);
            preparedStatement.setDate(i++, new Date(epoch));
            System.out.println(delivery_method);
            preparedStatement.setString(i++, delivery_method);
            System.out.println(address);
            preparedStatement.setString(i++, address);
            System.out.println(city);
            preparedStatement.setString(i++, city);
            System.out.println(state);
            preparedStatement.setString(i++, state);
            System.out.println(zip);
            preparedStatement.setString(i++, zip);
            System.out.println(country);
            preparedStatement.setString(i++, country);
            System.out.println(cardname);
            preparedStatement.setString(i++, cardname);
            System.out.println(last_four);
            preparedStatement.setString(i++, last_four);
            
//            for (String param : params) {
//            	System.out.println(param);
//                preparedStatement.setString(i++, param);
//
//            }
//            System.out.println(total);
            

            return preparedStatement.executeUpdate() > 0 ;

        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
    }
    
    public static boolean performReceiptDBUpdate(Connection connection, String sql, int orderid, int carid, String modelid, int quantity) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);


            int i = 1;
            System.out.println(orderid);
            preparedStatement.setInt(i++, orderid);
            System.out.println(carid);
            preparedStatement.setInt(i++, carid);
            System.out.println(modelid);
            preparedStatement.setString(i++, modelid);
            System.out.println(quantity);
            preparedStatement.setInt(i++, quantity);

            return preparedStatement.executeUpdate() > 0 ;

        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
    }
}
