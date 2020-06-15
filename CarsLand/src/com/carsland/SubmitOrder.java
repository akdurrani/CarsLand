package com.carsland;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.*;
import org.apache.hc.core5.http.io.entity.StringEntity;



//@WebServlet("/order")
public class SubmitOrder extends HttpServlet {
	static String orderid = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());

		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("first");
		String lname = request.getParameter("last");
		String phone = request.getParameter("phone");
//		System.out.println(phone);
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
//		System.out.println(state);
		String zip = request.getParameter("zip");
		String cardholder = request.getParameter("cardholder");
		String cardnumber = request.getParameter("cardnumer");
		if(cardnumber != null)
			cardnumber = cardnumber.substring(12);
		String cvv = request.getParameter("cvv");
		String expire_month = request.getParameter("expire-month");
		String expire_year = request.getParameter("expire-year");
		String delivery = request.getParameter("delivery");
		if(delivery == "3000"){
            delivery = "7-Day";
        }
        else if (delivery == "2000"){
            delivery = "14-Day";
        }
        else{
            delivery = "1-Month";
        }
		
	
		
		    
		    HttpSession session = request.getSession(true);
            LinkedList<String> cartList = new LinkedList<String>();
            if (session.isNew()) {
            	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			    rd.forward(request, response);
            }
            else {
            	cartList = (LinkedList<String>) session.getAttribute("cart");
                if(cartList == null){
                	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
    			    rd.forward(request, response);
                }
                else {
                	double subtotal =  (double) session.getAttribute("total");
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
                	String getOrderResponse =
			                target.path("order").
			                        request(). //send a request
			                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
			                        get(String.class); // use the get method and return the response as a string 
					
					ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

					Order lastOrder;
					
					lastOrder = objectMapper.readValue(getOrderResponse, Order.class);
					int lastorderid = lastOrder.getOrderId();
					lastorderid++;
                	System.out.println("Inserting records into the table...");
                	LocalDateTime myDateObj = LocalDateTime.now();
                	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                	String date = myFormatObj.toString();
//                	Entity params = Entity.json("{" 
//                			+ "\"orderId\"" + ": " + lastorderid + ", " 
//                			+ "\"fname\"" + ": \"" + fname + "\", " 
//                			+ "\"lname\"" + ": \"" + lname + "\", " 
//                			+ "\"phone\"" + ": \"" + phone + "\", " 
//                			+ "\"email\"" + ": \"" + email + "\", " 
//                			+ "\"date\"" + ": \"" + LocalDateTime.now() + "\", " 
//                			+ "\"delivery\"" + ": \"" + delivery + "\", '" 
//                			+ "\"addr\"" + ": \"" + address + "\", " 
//                			+ "\"city\"" + ": \"" + city + "\", " 
//                			+ "\"state\"" + ": \"" + state + "\", " 
//                			+ "\"zip\"" + ": \"" + zip + "\", " 
//                			+ "\"orderid\"" + ": " + "\"United States\", " 
//                			+ "\"cardname\"" + ": \"" + cardholder + "\", " 
//                			+ "\"lastFour\"" + ": \"" + cardnumber + "\", " 
//                			+ "\"total\"" + ": " + subtotal + "}");
//                	System.out.println(params);
                	Map<String, Object> params = new HashMap<>();
                	params.put("orderid", lastorderid);
                	params.put("fname", fname);
                	params.put("lname", lname);
                	params.put("phone", phone);
                	params.put("email", email);
//                	LocalDateTime time = LocalDateTime.now();
                	Timestamp time = new Timestamp(System.currentTimeMillis());
//                    ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
//                    ZonedDateTime zdt = time.atZone(zoneId);
//                    long epoch = zdt.toInstant().toEpochMilli();
                    System.out.println("in submit order time: "+time);
                	params.put("date", time);
                	params.put("delivery", delivery);
                	params.put("addr", address);
                	params.put("city", city);
                	params.put("state", state);
                	params.put("zip", zip);
                	params.put("country", "U.S.A");
                	params.put("cardname", cardholder);
                	params.put("lastFour", cardnumber);
                	params.put("total", subtotal);
                	
//                	try {
//						params.put("orderid", lastorderid);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("fname", fname);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("lname", lname);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("phone", phone);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("email", email);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("date", LocalDateTime.now().toString());
//					} catch (JSONException e3) {
//						// TODO Auto-generated catch block
//						e3.printStackTrace();
//					}
//                	try {
//						params.put("delivery", delivery);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("addr", address);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("city", city);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("state", state);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("zip", email);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("country", "U.S.A");
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("cardname", cardholder);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("lastFour", cardnumber);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//                	try {
//						params.put("total", subtotal);
//					} catch (JSONException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
                	
                	String json = (new ObjectMapper()).writeValueAsString(params);
                	Entity<String> params1 = Entity.json(json);
                	
//                	stmt.executeUpdate(orderQuery);
                	Response orderResponse = target.path("order").
                    request().post(params1);
                	System.out.println(orderResponse);
                	System.out.println("Inserted records into the table...");
                	String getOrderResponse2 =
			                target.path("order").
			                        request(). //send a request
			                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
			                        get(String.class); // use the get method and return the response as a string 
					
					ObjectMapper objectMapper2 = new ObjectMapper(); // This object is from the jackson library

					Order order;
					
					order = objectMapper2.readValue(getOrderResponse2, Order.class);
					int orderid = order.getOrderId();
					System.out.println(orderid);
                	cartObj.keys().forEachRemaining((key ->
            	    {
            	    	
            	    	String key_num = new String((String) key);
									int quantity;
									try {
										quantity = (int) cartObj.get(key_num);
										System.out.println(quantity);
										String carResponse =
								                target.path("car").path("" + key_num).
								                        request(). //send a request
								                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
								                        get(String.class);
										ObjectMapper objectMapper3 = new ObjectMapper(); // This object is from the jackson library

										Car car;
										try {
											car = objectMapper3.readValue(carResponse, Car.class);
											if(car != null) {
//												Entity<String> receiptParams = Entity.json("{"  
//																		+ "\"orderid\"" + ": " + orderid + ", "
//																		+ "\"carid\"" + ": " + car.getId() + ", "
//																		+ "\"modelid\"" + ": \"" + car.getModelId() + "\", " 
//																		+ "\"quantity\"" + ": " + quantity + "}");
												System.out.println("id:" + car.getId() + " modelid:" + car.getModelId());
												Map<String, Object> receiptParams = new HashMap<>();
												receiptParams.put("orderid", orderid);
												receiptParams.put("carid", car.getId());
												receiptParams.put("modelid", car.getModelId());
												receiptParams.put("quatity", quantity);
							                	System.out.println(receiptParams);
							                	
							                	String json1 = (new ObjectMapper()).writeValueAsString(receiptParams);
							                	Entity<String> params2 = Entity.json(json1);
												Response receiptResponse = target.path("receipt").
							                    request().post(params2);
												System.out.println(receiptResponse);
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
									} catch (JSONException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
//									
									
										
									
									
									
									
							
							
							
						
                    	
            	    	
            	    }));
                	
                	
//                	session.setAttribute("cart", null);
//                	session.setAttribute("total", 0);
                	System.out.println(orderid);
                	RequestDispatcher rd=request.getRequestDispatcher("orderDetails?id="+orderid);
    			    rd.forward(request, response);
                		
                }
            }

            
		    
//		    ResultSet cars = stmt.executeQuery ("select car.carid, modelCar.year, modelCar.make, modelCar.model, modelCar.color, modelCar.image, car.price, car.mileage from modelCar, car where modelCar.modelid = car.modelid");
		    
		    
	}

}
