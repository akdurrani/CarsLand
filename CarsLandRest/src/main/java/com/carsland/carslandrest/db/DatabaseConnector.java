package com.carsland.carslandrest.db;


import java.sql.*;


public class DatabaseConnector {


    private DatabaseConnector() {

    }

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cars_land?serverTimezone=UTC",
                    "root", "PINpan123");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


}

