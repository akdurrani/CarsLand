package com.carsland;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import java.io.*;
import java.sql.*;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference; 
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

//@WebServlet("/orderDetails")
public class OrderDetails extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());

		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		
		
		    
		    HttpSession session = request.getSession(true);
		    if (session.isNew()) {
            	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			    rd.forward(request, response);
            }
            else {
            	LinkedList<String> cartList = (LinkedList<String>) session.getAttribute("cart");
                if(cartList == null){
                	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
    			    rd.forward(request, response);
                }
                else {
            	System.out.println("order details");
            	String getOrderResponse =
		                target.path("order").path("/"+id).
		                        request(). //send a request
		                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
		                        get(String.class); // use the get method and return the response as a string 
				
				ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

				Order order;
				
				order = objectMapper.readValue(getOrderResponse, Order.class);
    		    
    		    if(order != null) {
    		    
            	out.print("<!DOCTYPE html>\n" + 
    		    		"<html lang=\"en\">\n" + 
    		    		"<head>\n" + 
    		    		"    <meta charset=\"UTF-8\">");
    		    out.print("<title> ORDER </title>");
    		    
    		    out.print("<link rel=\"stylesheet\" href=\"stylesheet/style.css\" type=\"text/css\">\n" + 
    		    		"    <link href=\"https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap\" rel=\"stylesheet\">\n" + 
    		    		"</head>\n" + 
    		    		"<body onload=\"orderTotal("+ order.getTotal()+")\">");
    		    out.print("<nav class=\"nav-bar blue description font-regular\">\n" + 
    		    		"    <div class=\"logo\">\n" + 
    		    		"        <a href=\"index.jsp\"> CARS | LAND</a>\n" + 
    		    		"    </div>\n" + 
    		    		"    <label for=\"toggle\">&#9776</label>\n" + 
    		    		"    <input type=\"checkbox\" id=\"toggle\"/>\n" + 
    		    		"\n" + 
    		    		"    <div class=\"nav-bar-items\">\n" + 
    		    		"        <a href=\"index.jsp\">HOME</a>\n" + 
    		    		"        <a href=\"index.jsp#cars\">CARS FOR SALE</a>\n" + 
    		    		"        <a href=\"index.jsp#about-us\">ABOUT</a>\n" + 
    		    		"        <a href=\"index.jsp#contact-us\">CONTACT US</a>\n"+
    		    		"        <a href=\"cart\" id=\"cart-logo\">");
    		    
    		    RequestDispatcher rd=request.getRequestDispatcher("cartLogo");
    		    rd.include(request, response);
    		    out.print("		</a>"+
    		    		"    </div>\n" + 
    		    		"</nav>");
    		    System.out.println("In order details. zip = " + order.getZip());
    		    
//    		    String taxResponse =
//    	                target.path("tax").path("" + order.getZip()).
//    	                        request(). //send a request
//    	                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
//    	                        get(String.class); // use the get method and return the response as a string 
//    			
//    			ObjectMapper objectMapper1 = new ObjectMapper(); // This object is from the jackson library
//
//    			Tax tax;
//    			
//    			tax = objectMapper1.readValue(taxResponse, Tax.class);
    			double rate = 0;
//				if (tax != null) {
//				    	rate = tax.getRate();
//			    }
    		    
    		    
	    		    out.print("<div class=\"\" id=\"\">\n" + 
	    		    		"        <div class=\"\">\n" + 
	    		    		"            <div class=\"container center\" >\n" + 
	    		    		"                <div class=\"container center\" id=\"confirmation\">\n" + 
	    		    		"                    <div class=\"conf-title\">THANKS FOR YOUR ORDER!</div>\n" + 
	    		    		"                    <div class=\"conf-subtitle\">We're currently processing your order, here are the details</div>\n" + 
	    		    		"                </div>\n" + 
	    		    		"                <div class=\"container\">\n" + 
	    		    		"                    <div class=\"card\">\n" + 
	    		    		"                        <div class=\"form-container\">\n" + 
	    		    		"                            <div class=\"col-6 in-line\" >\n" + 
	    		    		"                            <table class=\"left\">\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Your confirmation was sent to : </td>\n" 
	    		    		+ "                                        <td id=\"\">\n" + order.getemail() + 
	    		    		"                                        </td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Order Number :</td>\n" + 
	    		    		"                                        <td>\n" + order.getOrderId()+
	    		    		"                                        </td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Order Date :</td>\n" + 
	    		    		"                                        <td id=\"\">\n" + order.getDate()+
	    		    		"                                        </td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Delivery Method :</td>\n" + 
	    		    		"                                        <td id=\"\">" +order.getDelivery() +
	    		    		"                                        </td>" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Payment Information:</td>\n" + 
	    		    		"                                        <td></td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Cardholder Name:</td>\n" + 
	    		    		"                                        <td id=\"\">\n" + order.getFname()+ " " + order.getLname() + 
	    		    		"                                        </td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                    <tr>\n" + 
	    		    		"                                        <td>Card Number:</td>\n" + 
	    		    		"                                        <td id=\"\">\n" + 
	    		    		"                                                XXXX-XXXX-XXXX-"+ order.getLastFour() + 
	    		    		"                                        \n" + 
	    		    		"                                        </td>\n" + 
	    		    		"\n" + 
	    		    		"                                    </tr>\n" + 
	    		    		"                                </table>\n" + 
	    		    		"                            </div>\n" + 
	    		    		"                            <div class=\"col-6 in-line\" >\n" + 
	    		    		"                                Summary:\n" + 
	    		    		"                                <div class=\"form-container\">\n" + 
	    		    		"\n" + 
	    		    		"                                    <span class=\"font-regular left\">Subtotal:</span>\n" + 
	    		    		"                                    <span class=\"right\" id=\"conf-price\">\n" + order.getTotal() +
	    		    		"                                        \n" + 
	    		    		"                                    </span><br><br>\n" + 
	    		    		"\n" 
	    		    		+ 
	    		    		"                                    <span class=\"font-regular left\">Shipping Method:</span>\n" + 
	    		    		"                                    <span class=\"font-regular right\" id=\"conf-delivery\">"+ order.getDelivery() +"</span><br><br>\n" +
	    		    		 
	    		    		"                                    <span class=\"font-regular left\">Shipping Price:</span>\n" + 
	    		    		"                                    <span class=\"font-regular right\" id=\"conf-delivery-price\"></span><br><br>\n" + 
	    		    		"\n" + 
	    		    		"                                    <span class=\"font-regular left\" id=\"conf-tax-title\">Sale's Tax("+ (rate/100.0) +"%):</span>\n" + 
	    		    		"                                    <span class=\"font-regular right\" id=\"conf-tax\">");
	    		    
	    		    out.print("									</span><br><br>\n" + 
	    		    		"\n" + 
	    		    		"                                    <span class=\"font-regular left\">Total:</span>\n" + 
	    		    		"                                    <span class=\"right\" id=\"conf-total\"></span>\n" + 
	    		    		"                                </div>\n" + 
	    		    		"\n" +  
	    		    		"                            </div>\n" + 
	    		    		"                        </div>\n" + 
	    		    		"                    </div>\n" + 
	    		    		"                </div>     \n" + 
	    		    		"            </div>\n" + 
	    		    		"        </div>\n" + 
	    		    		"    </div>");
    		    
    		    	}
    		    
    		    
    		    out.print("<footer class=\"clear-float\">\n" + 
    		    		"    <div class=\"logo\">\n" + 
    		    		"        <a href=\"index.jsp\"> CARS | LAND</a>\n" + 
    		    		"    </div>\n" + 
    		    		"    <div class=\"credit\">\n" + 
    		    		"        <a href=\"credits.html\">Content Credits</a>\n" + 
    		    		"    </div>\n" + 
    		    		"</footer>\n" + 
    		    		"\n" + 
    		    		"</body>\n" + 
    		    		"<script src=\"javascript/carsland.js\"></script>\n" + 
    		    		"\n" + 
    		    		"</html>");
                }
                
                session.setAttribute("cart", null);
            	session.setAttribute("total", 0.0);
    		    
            }
		
			
	}
}
