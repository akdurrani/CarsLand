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
import javax.servlet.annotation.WebServlet;


//@WebServlet("/cart")
public class Cart extends HttpServlet {
	static double total = 0;
	//loads cart page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());

 
		PrintWriter out = response.getWriter();
		
		    
		    HttpSession session = request.getSession(true);
            LinkedList<String> cartList = null;

            out.print("<!DOCTYPE html>\n" + 
		    		"<html lang=\"en\">\n" + 
		    		"<head>\n" + 
		    		"    <meta charset=\"UTF-8\">");
		    out.print("<title> CART </title>");
		    
		    out.print("<link rel=\"stylesheet\" href=\"stylesheet/style.css\" type=\"text/css\">\n" + 
		    		"    <link href=\"https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap\" rel=\"stylesheet\">\n" + 
		    		"</head>\n" + 
		    		"<body>");
		    out.print("<nav class=\"nav-bar blue description font-regular\">\n" + 
		    		"    <div class=\"logo\">\n" + 
		    		"        <a href=\"index.jsp\"> CARS | LAND</a>\n" + 
		    		"    </div>\n" + 
		    		"    <label for=\"toggle\">&#9776</label>\n" + 
		    		"    <input type=\"checkbox\" id=\"toggle\"/>\n" + 
		    		"\n" + 
		    		"    <div class=\"nav-bar-items\" id=\"cart-logo\">\n" + 
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
		    
            
            if (session.isNew()) {
            	System.out.println("newSession");
                cartList = new LinkedList<String>();
                session.setAttribute("cart", cartList);
                out.print("<div class=\"container center\" >\n" + 
                		"  <div class=\"conf-title\" >\n" +
    		    		"            CART EMPTY\n" + 
    		    		"        </div></div>");
            }
            else {
                cartList = (LinkedList<String>) session.getAttribute("cart");
                if(cartList.size() == 0){
                	System.out.println("nullList");
                    cartList = new LinkedList<String>();
                    session.setAttribute("cart", cartList);
                    out.print("<div class=\"container center\" >\n" + 
                    		"  <div class=\"conf-title\">\n" +
        		    		"            CART EMPTY\n" + 
        		    		"        </div></div>");
                }
                else{
                	System.out.println("jsonObj");
                	JSONObject cartObj = new JSONObject();
                	for(int i=0; i < cartList.size(); i++){
                		String key = ((LinkedList<String>) cartList).get(i);
//                		System.out.println(key);
                		if(cartObj.has(key)) {
							try {
								cartObj.increment(key);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                		}
                		else {
	                		try {
								cartObj.put(key, 1);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		}
                		
                		
                	}
                	out.print("<div class=\"container center\" >\n" + 
                    		"  <div class=\"conf-title\" >\n" +
        		    		"            CART\n" + 
        		    		"        </div></div>");
            		out.print("<div class=\"cart\">");
            		out.print("<div class=\"container\">"
            				+ "<div class=\"row\"><div class=\"col-8 col-sm-7 in-line\">");
            		
            		
            		out.print("<div class=\"row\">");
            		
            		total = 0;
            		cartObj.keys().forEachRemaining((key ->
            	    {
//            	    	System.out.println(key);
            	    	String key_num = new String((String) key);
//            	    	System.out.println(key_num);
            	    	try {
							int quantity = (int) cartObj.get(key_num);
//							System.out.println(quantity);
//							String query = "select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid and carid = " + key;
		                	
//							ResultSet cars = stmt.executeQuery(query);
							String jsonResponse =
					                target.path("car").path("" + key).
					                        request(). //send a request
					                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
					                        get(String.class); // use the get method and return the response as a string 
							
							ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

							Car cars;
							try {
								cars = objectMapper.readValue(jsonResponse, Car.class);
								if(cars != null) {
									out.print("<div class=\"col-11 card-space card in-line\" id=\"cart-item\">"
											+ "<a class=\"font-black float-right\" href=\"\">"
			                				+ "Remove"
			                				+ "</a>"
			                				+ "<a href=\"/CarsLand/product?id="+ key +"\">"
			                				+ "<span class=\"col-6 col-sm-5 left in-line\">"
			                				+ "<div class=\"card-img\">" 
			                				+ "<img src=\"images/"+ cars.getImage() +"\" alt=\"\">" 
			                				+ "</div>"
			                				+ "</span>"
			                				+ "<span class=\"col-6 col-sm-5 font-black right in-line\">"
			                				+ "<div class=\"card-title\">"+ cars.getYear() + " " + cars.getMake() + " " + cars.getModel() +"</div>"
			                				+ "<div class=\"card-detail\">Color: "+ cars.getColor()+" | "+cars.getMileage()+"</div>" 
			                				+ "Quantity: " + quantity
			                				+ "<div class=\"card-price\">Price: "+cars.getPrice()+"</div>"
			                				+ "</span>"
			                				+ "</a>"
			                				+ "</div>");
										total += (double) Integer.parseInt(cars.getPrice().replace(",", "").replace("$", "").trim()) * (double) quantity;
				                }
							} catch (JsonParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JsonMappingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    	
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	    }));
            		
            		
            		out.print("</div>");
            		
            		out.print("</div>"
            				+ "<div class=\"col-3 col-sm-4 cart-card in-line\" >"
            				+ "<div class=\"order\">"
            				+ "<div class=\"form-container\">"
            				+ "<div class=\"card-price\">"
            				+ "<span class=\"font-regular left\">Subtotal:</span>"
            				+ "<span class=\"right\" id=\"price\">$"+ total +"</span><br><br>"
            				+ "</div>"
            				+ "<div class=\"center\" id=\"checkout-button\" >"
            				+ "<a href=\"/CarsLand/checkout\"><button class=\"button large font-regular\">PROCEED TO CHECKOUT</button></a>"
            				+ "</div>"
            				+ "</div>"
            				+ "</div>"
            				+ "</div>"
            				+ "</div>");
            		out.print("</div></div>");
//            		System.out.println(total);
            		session.setAttribute("total", total);
                }
                
                
                
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
	
}
