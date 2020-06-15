
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


//@WebServlet("/cartLogo")

public class CartLogo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
//		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession(true);
         
         if (session.isNew()) {
        	 LinkedList<String> cartList = (LinkedList<String>) session.getAttribute("cart");
        	 session.setAttribute("cart", cartList);
             out.print("CART(0)");
         }
         else {
             LinkedList<String> cartList = (LinkedList<String>) session.getAttribute("cart");
             if(cartList == null){
                 cartList = new LinkedList<String>();
                 session.setAttribute("cart", cartList);
                 out.print("CART(0)");
             }
             else{
            	 out.print("CART("+ cartList.size() +")");
             }
         }
		
//		RequestDispatcher rd=request.getRequestDispatcher("/index.html");
//		rd.include(request, response);
		
//		out.print("This is a test");
//		out.print("<div class=\"row\"><div class=\"col-3 card-space\"><div class=\"card effect\">This is a test</div></div></div>");
		
	}
	
}
