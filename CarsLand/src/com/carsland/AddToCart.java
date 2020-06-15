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


//@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String car_id = request.getParameter("id");
		
		 HttpSession session = request.getSession(true);
		 
		 if (session.isNew()) {
			 LinkedList<String> cartList = (LinkedList<String>) session.getAttribute("cart");
			 cartList.add(car_id);
			 session.setAttribute("cart", cartList);
		     out.print("CART(1)");
		 }
		 else {
		     LinkedList<String> cartList = (LinkedList<String>) session.getAttribute("cart");
		     if(cartList == null){
		         cartList = new LinkedList<String>();
		         cartList.add(car_id);
		         session.setAttribute("cart", cartList);
		         out.print("CART(1)");
		     }
		     else{
		    	 cartList.add(car_id);
		    	 out.print("CART("+ cartList.size() +")");
		     }
		 }
	}
}
