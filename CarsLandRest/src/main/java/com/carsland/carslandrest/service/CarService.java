package com.carsland.carslandrest.service;

import com.carsland.carslandrest.db.DatabaseConnector;
import com.carsland.carslandrest.db.DatabaseUtils;
import com.carsland.carslandrest.objects.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {
	 private final static String ALL_CARS_QUERY = "SELECT * FROM modelCar, car";
	
	 public static Car getCarById(int id) {
	        //Get a new connection object before going forward with the JDBC invocation.
	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_CARS_QUERY + " WHERE modelCar.modelid = car.modelid AND carid = " + id);

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Car car = new Car();


	                    car.setId(resultSet.getInt("carid"));
	                    car.setModelId(resultSet.getString("modelid"));
	                    car.setPrice(resultSet.getString("price"));
	                    car.setMileage(resultSet.getString("mileage"));
	                    car.setYear(resultSet.getString("year"));
	                    car.setMake(resultSet.getString("make"));
	                    car.setModel(resultSet.getString("model"));
	                    car.setYear(resultSet.getString("color"));
	                    car.setImage(resultSet.getString("image"));
	                    car.setImage1(resultSet.getString("image1"));
	                    car.setImage2(resultSet.getString("image2"));
	                    car.setImage3(resultSet.getString("image3"));
	                    car.setFeatures(resultSet.getString("feature"));
	                    car.setSpecs(resultSet.getString("specification"));

	                    return car;

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
	 
	 public static List<Car> getAllCars() {
	        List<Car> cars = new ArrayList<Car>();

	        Connection connection = DatabaseConnector.getConnection();
	        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_CARS_QUERY + " WHERE modelCar.modelid = car.modelid");

	        if (resultSet != null) {
	            try {
	                while (resultSet.next()) {
	                    Car car = new Car();

	                    car.setId(resultSet.getInt("carid"));
	                    car.setModelId(resultSet.getString("modelid"));
	                    car.setPrice(resultSet.getString("price"));
	                    car.setMileage(resultSet.getString("mileage"));
	                    car.setYear(resultSet.getString("year"));
	                    car.setMake(resultSet.getString("make"));
	                    car.setModel(resultSet.getString("model"));
	                    car.setYear(resultSet.getString("color"));
	                    car.setImage(resultSet.getString("image"));
	                    car.setImage1(resultSet.getString("image1"));
	                    car.setImage2(resultSet.getString("image2"));
	                    car.setImage3(resultSet.getString("image3"));
	                    car.setFeatures(resultSet.getString("feature"));
	                    car.setSpecs(resultSet.getString("specification"));

	                    cars.add(car);

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

	        return cars;
	    }
	 
}
