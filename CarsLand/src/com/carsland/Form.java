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

//@WebServlet("/checkout")
public class Form extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());

		response.setContentType("text/html"); 
		
		PrintWriter out = response.getWriter();
		
		    
		    HttpSession session = request.getSession(true);
            LinkedList<String> cartList = new LinkedList<String>();

            
		    
            
            if (session.isNew()) {
            	System.out.println("newSession");
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
                out.print("<div class=\"container center\" >\n" + 
                		"  <div class=\"conf-title\" >\n" +
    		    		"            CHECKOUT\n" + 
    		    		"        </div></div>");
            	
        		out.print("<div>");
        		out.print("<div class=\"container\">"
        				+ "<div class=\"row center font-black\"><h3>NO ITEMS IN CART</h3></div></div></div>");
//            	RequestDispatcher newsesh =request.getRequestDispatcher("index.html");
//			    newsesh.forward(request, response);
            }
            else {
                cartList = (LinkedList<String>) session.getAttribute("cart");
                if(cartList == null){
                	System.out.println("nullList");
                    cartList = new LinkedList<String>();
                    session.setAttribute("cart", cartList);
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
                    out.print("<div class=\"container center\" >\n" + 
                    		"  <div class=\"conf-title\" >\n" +
        		    		"            CHECKOUT\n" + 
        		    		"        </div></div>");
                    
            		out.print("<div>");
            		out.print("<div class=\"container\">"
            				+ "<div class=\"row center font-black\"><h3>NO ITEMS IN CART</h3></div></div></div>");
//                    RequestDispatcher newsesh =request.getRequestDispatcher("index.html");
//    			    newsesh.forward(request, response);
                }
                
                else{
                	double subtotal = (double) session.getAttribute("total");
                	out.print("<!DOCTYPE html>\n" + 
        		    		"<html lang=\"en\">\n" + 
        		    		"<head>\n" + 
        		    		"    <meta charset=\"UTF-8\">");
        		    out.print("<title> CART </title>");
        		    
        		    out.print("<link rel=\"stylesheet\" href=\"stylesheet/style.css\" type=\"text/css\">\n" + 
        		    		"    <link href=\"https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap\" rel=\"stylesheet\">\n" + 
        		    		"</head>\n" + 
        		    		"<body onload=\"calculateTotal("+subtotal+")\">");
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
//                	out.print("<div class=\"container center\" >\n" + 
//                    		"  <div class=\"conf-title\" >\n" +
//        		    		"            CHECKOUT\n" + 
//        		    		"        </div></div>");
            		out.print("<div>");
            		out.print("<div class=\"container\">"
            				+ "<div class=\"row center\">");
            		
            		out.print("<div class=\"col-8 col-sm-12 in-line left\">"
            				+ " <div class=\"order-form\">"
            				+ "<div class=\"container\">" 
            				+"<form name=\"customer\" action=\"order\" onsubmit=\"\" method=\"post\">" );
            				
            		out.print("<div class=\"form-container\" id=\"customer-info\">\n" + 
            				"                            <fieldset>\n" + 
            				"                                <h3 class=\"font-black\">Owner</h3>\n" + 
            				"                                <input type=\"text\" name=\"first\" id=\"first\" placeholder=\"First Name\" required>\n" + 
            				"                                <input type=\"text\" name=\"last\" id=\"last\" placeholder=\"Last Name\" required><br><br>\n" + 
            				"                                <input type=\"text\" name=\"phone\" id=\"phone\" placeholder=\"Phone Number\" pattern=\".{10,}\" oninvalid=\"this.setCustomValidity('Invalid phone number')\" oninput=\"setCustomValidity('')\" required><br><br>\n" + 
            				"                                <input type=\"email\" name=\"email\" id=\"email\"  placeholder=\"Email Address\" oninvalid=\"this.setCustomValidity('Invalid email')\" oninput=\"setCustomValidity('')\" required><br><br>\n" + 
            				"                                <h3 class=\"font-black\">Shipping Information</h3>\n" + 
            				"                                <input type=\"text\" name=\"address\" id=\"shipping-addr\" placeholder=\"Shipping Address\" required><br><br>\n" + 
            				"                                <input type=\"text\" name=\"city\" id=\"city\" placeholder=\"City\" required>\n" + 
            				"                                <select id=\"state\" name=\"state\" onchange=\"resetZip()\">\n" + 
            				"                                    <option value=\"AL\">Alabama</option>\n" + 
            				"                                    <option value=\"AK\">Alaska</option>\n" + 
            				"                                    <option value=\"AZ\">Arizona</option>\n" + 
            				"                                    <option value=\"AR\">Arkansas</option>\n" + 
            				"                                    <option value=\"CA\">California</option>\n" + 
            				"                                    <option value=\"CO\">Colorado</option>\n" + 
            				"                                    <option value=\"CT\">Connecticut</option>\n" + 
            				"                                    <option value=\"DE\">Delaware</option>\n" + 
            				"                                    <option value=\"DC\">District Of Columbia</option>\n" + 
            				"                                    <option value=\"FL\">Florida</option>\n" + 
            				"                                    <option value=\"GA\">Georgia</option>\n" + 
            				"                                    <option value=\"HI\">Hawaii</option>\n" + 
            				"                                    <option value=\"ID\">Idaho</option>\n" + 
            				"                                    <option value=\"IL\">Illinois</option>\n" + 
            				"                                    <option value=\"IN\">Indiana</option>\n" + 
            				"                                    <option value=\"IA\">Iowa</option>\n" + 
            				"                                    <option value=\"KS\">Kansas</option>\n" + 
            				"                                    <option value=\"KY\">Kentucky</option>\n" + 
            				"                                    <option value=\"LA\">Louisiana</option>\n" + 
            				"                                    <option value=\"ME\">Maine</option>\n" + 
            				"                                    <option value=\"MD\">Maryland</option>\n" + 
            				"                                    <option value=\"MA\">Massachusetts</option>\n" + 
            				"                                    <option value=\"MI\">Michigan</option>\n" + 
            				"                                    <option value=\"MN\">Minnesota</option>\n" + 
            				"                                    <option value=\"MS\">Mississippi</option>\n" + 
            				"                                    <option value=\"MO\">Missouri</option>\n" + 
            				"                                    <option value=\"MT\">Montana</option>\n" + 
            				"                                    <option value=\"NE\">Nebraska</option>\n" + 
            				"                                    <option value=\"NV\">Nevada</option>\n" + 
            				"                                    <option value=\"NH\">New Hampshire</option>\n" + 
            				"                                    <option value=\"NJ\">New Jersey</option>\n" + 
            				"                                    <option value=\"NM\">New Mexico</option>\n" + 
            				"                                    <option value=\"NY\">New York</option>\n" + 
            				"                                    <option value=\"NC\">North Carolina</option>\n" + 
            				"                                    <option value=\"ND\">North Dakota</option>\n" + 
            				"                                    <option value=\"OH\">Ohio</option>\n" + 
            				"                                    <option value=\"OK\">Oklahoma</option>\n" + 
            				"                                    <option value=\"OR\">Oregon</option>\n" + 
            				"                                    <option value=\"PA\">Pennsylvania</option>\n" + 
            				"                                    <option value=\"RI\">Rhode Island</option>\n" + 
            				"                                    <option value=\"SC\">South Carolina</option>\n" + 
            				"                                    <option value=\"SD\">South Dakota</option>\n" + 
            				"                                    <option value=\"TN\">Tennessee</option>\n" + 
            				"                                    <option value=\"TX\">Texas</option>\n" + 
            				"                                    <option value=\"UT\">Utah</option>\n" + 
            				"                                    <option value=\"VT\">Vermont</option>\n" + 
            				"                                    <option value=\"VA\">Virginia</option>\n" + 
            				"                                    <option value=\"WA\">Washington</option>\n" + 
            				"                                    <option value=\"WV\">West Virginia</option>\n" + 
            				"                                    <option value=\"WI\">Wisconsin</option>\n" + 
            				"                                    <option value=\"WY\">Wyoming</option>\n" + 
            				"                                </select>\n" + 
            				"                                <input type=\"text\" name=\"zip\" id=\"zip\" placeholder=\"Zip Code\" pattern=\"^[0-9]{5}$\" oninvalid=\"this.setCustomValidity('Invalid zipcode')\" oninput=\"setCustomValidity('')\" onchange=\"onChangeZip(this.value)\" required><br><br>"
            				+ "<span class=\"font-black\" id=\"country\" name=\"country\" value=\"United States\">United States</span><br><br>"
            				+ "<h3 class=\"font-black\">Billing Information</h3>"
            				+ "<input type=\"text\" name=\"cardholder\" id=\"cardholder\" placeholder=\"Cardholder's Name\" required><br><br>\n" + 
            				"                                <input type=\"text\" name=\"cardnumber\" id=\"cardnumber\"  placeholder=\"Card Number\" pattern=\"^[0-9]{16}$\" oninvalid=\"this.setCustomValidity('Invalid credit card number')\" oninput=\"setCustomValidity('')\" required>\n" + 
            				"                                <input type=\"text\" name=\"cvv\" id=\"cvv\" placeholder=\"CVV\" pattern=\"^[0-9]{3,4}$\" required><br><br>\n" + 
            				"                                <select name=\"expire-month\" id=\"expire-month\" >\n" + 
            				"                                    <option value=\"January\">Jan</option>\n" + 
            				"                                    <option value=\"Febuary\">Feb</option>\n" + 
            				"                                    <option value=\"March\">Mar</option>\n" + 
            				"                                    <option value=\"April\">Apr</option>\n" + 
            				"                                    <option value=\"May\">May</option>\n" + 
            				"                                    <option value=\"June\">Jun</option>\n" + 
            				"                                    <option value=\"July\">Jul</option>\n" + 
            				"                                    <option value=\"August\">Aug</option>\n" + 
            				"                                    <option value=\"September\">Sept</option>\n" + 
            				"                                    <option value=\"October\">Oct</option>\n" + 
            				"                                    <option value=\"November\">Nov</option>\n" + 
            				"                                    <option value=\"December\">Dec</option>\n" + 
            				"                                </select>\n" + 
            				"                                <select name=\"expire-year\" id=\"expire-year\" >\n" + 
            				"                                    <option value=\"2020\">2020</option>\n" + 
            				"                                    <option value=\"2021\">2021</option>\n" + 
            				"                                    <option value=\"2022\">2022</option>\n" + 
            				"                                    <option value=\"2023\">2023</option>\n" + 
            				"                                    <option value=\"2024\">2024</option>\n" + 
            				"                                    <option value=\"2025\">2025</option>\n" + 
            				"                                    <option value=\"2026\">2026</option>\n" + 
            				"                                    <option value=\"2027\">2027</option>\n" + 
            				"                                    <option value=\"2028\">2028</option>\n" + 
            				"                                    <option value=\"2029\">2029</option>\n" + 
            				"                                    <option value=\"2030\">2030</option>\n" + 
            				"                                </select>\n" + 
            				"                            </fieldset>\n" + 
            				"                        </div>");
            		
            		out.print("</div>"
            				+ "</div></div>");
            		
            		out.print("<div class=\"col-4 col-sm-12 cart-card in-line \">");
            		
            		
            		out.print("<div class=\"row\">"
            				+ "<div class=\"card-title\">Items:</div>");
            		
            		
            		//loop to list all items in cart
            		
            		cartObj.keys().forEachRemaining((key ->
            	    {
//            	    	System.out.println(key);
            	    	String key_num = new String((String) key);
//            	    	System.out.println(key_num);
            	    	try {
							int quantity = (int) cartObj.get(key_num);
//							System.out.println(quantity);
//							String query = "select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid and carid = " + key;
		                	
							
//								ResultSet cars = stmt.executeQuery(query);
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
									out.print("<div class=\"col-8 col-sm-8 card-space in-line\" id=\"cart-item\">"
			                				+ "<span class=\"col-6 col-sm-3 font-black left in-line\">"
			                				+ "<div class=\"card-detail\">"+ cars.getYear() + " " + cars.getMake() + " " + cars.getModel() +"</div>"
			                				+ "<div class=\"card-detail\">Color: "+ cars.getColor()+" | "+cars.getMileage()+"</div>"
			                				+ "</span>"
			                				+ "<span class=\"col-6 col-sm-3 font-black right in-line\">" 
			                				+ "Quantity: " + quantity
			                				+ "<div class=\"card-detail\">Price: "+cars.getPrice()+"</div>"
			                				+ "</span>"
			                				+ "</div>");
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
            		
            		out.print("<div class=\"row\">"
            				+ "<div class=\"order\">"
            				+ "<div class=\"form-container\">"
            				+ "Delivery Option: <select name=\"delivery\" id=\"delivery\" onchange=\"updateDelivery()\">\n" 
            				+"<option value=\"3000\" selected=\"selected\">7-Day</option>\n" 
            				+"<option value=\"2000\">14-Day</option>\n" 
            				+"<option value=\"1000\">1-Month</option>\n" 
            				+"</select>"
            				+ "<div class=\"card-price\">"
            				+ "<span class=\"font-regular left\">Subtotal:</span>"
            				+ "<span class=\"right\" id=\"price\">$"+ subtotal +"</span><br><br>"
            				+ "<span class=\"font-regular left\">Shipping & Handling:</span>"
            				+ "<span class=\"font-regular right\" id=\"delivery-price\">$3000</span><br><br>"
            				+ "<span class=\"font-regular left\" id=\"tax-title\">Sales Tax (0.0%)</span>" 
            				+ "<span class=\"font-regular right\" id=\"tax\">0.0</span><br><br>"
            				+ "<span class=\"font-regular left\">Total:</span>" 
            				+ "<span class=\"right\" id=\"total\"></span>"
            				+ "</div>"
            				+ "<div class=\"center\" id=\"checkout-button\" >"
            				+ "<button class=\"button large font-regular\" value=\"Submit\">CHECKOUT</button>"
            				+ "</div>"
            				+ "</div>"
            				+ "</div>");
            		out.print("</div>"
            				+ "</form>"
            				+ "</div>");
            		
            		
            		out.print("</div></div></div>");
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
