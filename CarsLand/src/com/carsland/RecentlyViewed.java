package com.carsland;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;


//@WebServlet("/viewed")

public class RecentlyViewed extends HttpServlet {
	
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
		    
		    HttpSession session = request.getSession(true);
            LinkedList<String> viewList = null;

            if (session.isNew()) {
                viewList = new LinkedList<String>();
                session.setAttribute("views", viewList);
                out.print("<div class=\"container center\" id=\"section-title\">\n" + 
    		    		"            <h3 class=\"font-black\">NO RECENTLY VIEWED CARS</h3>\n" + 
    		    		"        </div>");
            }
            else {
                viewList = (LinkedList<String>) session.getAttribute("views");
                if(viewList == null){
                    viewList = new LinkedList<String>();
                    session.setAttribute("views", viewList);
                    out.print("<div class=\"container center\" id=\"section-title\">\n" + 
        		    		"            <h3 class=\"font-black\">NO RECENTLY VIEWED CARS</h3>\n" + 
        		    		"        </div>");
                }
                else{
                	out.print("<div class=\"container left\" id=\"section-title\">\n" + 
        		    		"            <h3 class=\"font-black\">RECENTLY VIEWED CARS:</h3>\n" + 
        		    		"        </div>");
                	out.print("<div class=\"container left\">\n" + 
        		    		"            <div class=\"row\">");
                	for(int i=0; i < viewList.size(); i++){
                		String id = ((LinkedList<String>) viewList).get(i);
                		String query = "select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid and carid = " + id;
	                	ResultSet cars = stmt.executeQuery(query);
	                	if(cars.next()) {
	                		out.print("<div class=\"col-2 card-space center recent-views\">\n" + 
	             		    		"                    <a href=\"product?id=" + id + "\">\n" + 
	             		    		"                    <div class=\"card effect\">\n" + 
	             		    		"                        <div class=\"card-img\">\n" + 
	             		    		"                            <img src=\"images/"+ cars.getString("image") +"\" alt=\"\">\n" + 
	             		    		"                        </div>\n" + 
	             		    		"                        <div class=\"card-detail\">"+ cars.getString("year") + " " + cars.getString("make") + " " + cars.getString("model") +"</div>\n" + 
	             		    		"                    </div>\n" + 
	             		    		"                    </a>\n" + 
	             		    		"                </div>");
	                	}
                	}
                	out.print("</div></div>");
                }
                
            } 
		    
//		    ResultSet cars = stmt.executeQuery ("select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid");
		    
		    
		    		
		    
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} 
		
//		RequestDispatcher rd=request.getRequestDispatcher("/index.html");
//		rd.include(request, response);
		
//		out.print("This is a test");
//		out.print("<div class=\"row\"><div class=\"col-3 card-space\"><div class=\"card effect\">This is a test</div></div></div>");
		
	}
	
}
