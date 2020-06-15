package com.carsland;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//@WebServlet("/home")

public class Home extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		Connection conn = null;
		String url       = "jdbc:mysql://localhost:3306/cars_land?serverTimezone=UTC";
	    String user      = "root";
	    String password  = "PINpan123";
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection(url, user, password);
		    Statement stmt = conn.createStatement();
		    ResultSet cars = stmt.executeQuery ("select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid");
		    int count = 0;
		    while (cars.next()) {
		    	out.print("<div class=\"col-3 card-space\">");
		    	out.print("<a href=\"product?id=" + count + "\">");
		    	out.print("<div class=\"card effect\">");
		    	out.print("<div class=\"card-img\">");
		    	out.print("<img src=\"/CarsLand/images/" + cars.getString("image") + "\" alt=\"\">");
		    	out.print("</div>");
		    	out.print("<div class=\"card-title\">" + cars.getString("year") + " " + cars.getString("make") + " " + cars.getString("model") + "</div>");
		    	out.print("<div class=\"card-detail\">Color: " + cars.getString("color") + " | " + cars.getString("mileage") + "</div>");
		    	out.print("<div class=\"card-price\">Price: " + cars.getString("price") + "</div>");
		    	out.print("</div>");
                out.print("</a>");
                out.print("</div>");
                count++;
		    }
		    //need to include recently viewed servlet
		    RequestDispatcher rd=request.getRequestDispatcher("viewed");
		    rd.include(request, response);
		    
		    		
		    
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} 
		
//		RequestDispatcher rd=request.getRequestDispatcher("/index.html");
//		rd.include(request, response);
		
//		out.print("This is a test");
//		out.print("<div class=\"row\"><div class=\"col-3 card-space\"><div class=\"card effect\">This is a test</div></div></div>");
		
	}
	
}
