package com.carsland;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 
import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

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


//@WebServlet("/product")
public class Descriptions extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id = request.getParameter("id");
		String idPath = "/" + id;
		ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());


        String jsonResponse =
                target.path("car").path(idPath).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string 
		
		ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

		Car desc = objectMapper.readValue(jsonResponse, Car.class);
    	
		
		PrintWriter out = response.getWriter();
		
		    
            HttpSession session = request.getSession(true);
            LinkedList<String> viewList = null;

            if (session.isNew()) {
                viewList = new LinkedList<String>();
                viewList.add(id);
                session.setAttribute("views", viewList);
            }
            else {
                viewList = (LinkedList<String>) session.getAttribute("views");
                if(viewList == null){
                    viewList = new LinkedList<String>();
                }
                
                boolean alreadyViewed = false;
                for(int i=0; i < viewList.size(); i++){
            		String view_id = ((LinkedList<String>) viewList).get(i);
//            		System.out.println(view_id);
            		if (view_id.compareTo(id) == 0) {
            			viewList.remove(i);
            			viewList.add(id);
                        session.setAttribute("views", viewList);
                        alreadyViewed = true;
                        break;
            		}
                }
            	if(!alreadyViewed) {
            		viewList.add(id);
            		if(viewList.size() > 5){
                        viewList.remove();
                    }
            		session.setAttribute("views", viewList);
            	}
            } 
		    
		    if(desc != null){
		    	String features = desc.getFeatures();
		    	String[] featureArray = features.split(",");
		    	String specs = desc.getSpecs();
		    	String[] specArray = specs.split(",");
		    	
//		    	System.out.println(specArray[0]);
			    out.print("<!DOCTYPE html>\n" + 
			    		"<html lang=\"en\">\n" + 
			    		"<head>\n" + 
			    		"    <meta charset=\"UTF-8\">");
			    out.print("<title>" + desc.getYear() + " " + desc.getMake() + " " + desc.getModel() + "</title>");
			    
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
			    		"        <a href=\"/CarsLand/cart\" id=\"cart-logo\">");
			    
			    RequestDispatcher rd=request.getRequestDispatcher("cartLogo");
			    rd.include(request, response);
			    
			    out.print("		</a>"+
			    		"    </div>\n" + 
			    		"</nav>");
			    
			    
			    out.print("<div id=\"description-page\">\n" + 
			    		"        <div class=\"container\">\n" + 
			    		"            <div class=\"col-6 float-left\">\n" + 
			    		"                <div class=\"description-img\" id=\"description-img\"><img src=\"/CarsLand/images/" + desc.getImage() + "\" alt=\"\">\n" + 
			    		"                    <br>\n" + 
			    		"\n" + 
			    		"                    <!-- Slideshow container source: https://www.w3schools.com/howto/howto_js_slideshow.asp --> \n" + 
			    		"                    <div class=\"slideshow-container\">\n" + 
			    		"                        <!-- Full-width images with number and caption text -->\n" + 
			    		"                        <div class=\"mySlides fade\">\n" + 
			    		"                            <div class=\"numbertext\">1 / 3</div>\n" + 
			    		"                            <img src=\"/CarsLand/images/" + desc.getImage1() + "\" style=\"width:100%\">\n" + 
			    		"                            <div class=\"text\">Interior</div>\n" + 
			    		"                        </div>\n" + 
			    		"\n" + 
			    		"                        <div class=\"mySlides fade\">\n" + 
			    		"                            <div class=\"numbertext\">2 / 3</div>\n" + 
			    		"                            <img src=\"/CarsLand/images/" + desc.getImage2() + "\" style=\"width:100%\">\n" + 
			    		"                            <div class=\"text\">Interior</div>\n" + 
			    		"                        </div>\n" + 
			    		"\n" + 
			    		"                        <div class=\"mySlides fade\">\n" + 
			    		"                            <div class=\"numbertext\">3 / 3</div>\n" + 
			    		"                            <img src=\"/CarsLand/images/" + desc.getImage3() + "\" style=\"width:100%\">\n" + 
			    		"                            <div class=\"text\">Interior</div>\n" + 
			    		"                        </div>\n" + 
			    		"\n" + 
			    		"                        <!-- Next and previous buttons -->\n" + 
			    		"                        <a class=\"prev\" onclick=\"plusSlides(-1)\">&#10094;</a>\n" + 
			    		"                        <a class=\"next\" onclick=\"plusSlides(1)\">&#10095;</a>\n" + 
			    		"                    </div>\n" + 
			    		"                    <br>\n" + 
			    		"\n" + 
			    		"                    <!-- The dots/circles -->\n" + 
			    		"                    <div class=\"dots-container\">\n" + 
			    		"                        <span class=\"dot\" onclick=\"currentSlide(1)\"></span>\n" + 
			    		"                        <span class=\"dot\" onclick=\"currentSlide(2)\"></span>\n" + 
			    		"                        <span class=\"dot\" onclick=\"currentSlide(3)\"></span>\n" + 
			    		"                    </div>\n" + 
			    		"                </div>\n" + 
			    		"            </div>\n" + 
			    		"            <div class=\"col-6 float-right\">\n" + 
			    		"                <div class=\"card description center\">\n" + 
			    		"                    <div class=\"description-container\">\n" + 
			    		"                        <div class=\"card-title\">" + desc.getYear() + " " + desc.getMake() + " " + desc.getModel() + "</div>\n" + 
			    		"                        <div class=\"card-detail\">Color: " + desc.getColor() + " | " + desc.getMileage() + "</div>\n" + 
			    		"                        <div class=\"card-feature\">Features <br><hr>\n" + 
			    		"                            <table>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>" + featureArray[0] + "</td>\n" + 
			    		"                                    <td>" + featureArray[1] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>" + featureArray[2] + "</td>\n" + 
			    		"                                    <td>" + featureArray[3] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>" + featureArray[4] + "</td>\n" + 
			    		"                                    <td>" + featureArray[5] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                            </table>\n" + 
			    		"                        </div>\n" + 
			    		"                        <div class=\"card-specs\">Specifications <br><hr>\n" + 
			    		"                            <table class=\"left\">\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>City/Highway Gas Mileage:</td>\n" + 
			    		"                                    <td>" + specArray[0] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Exterior/Interior Color:</td>\n" + 
			    		"                                    <td>" + specArray[1] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Transmission:</td>\n" + 
			    		"                                    <td>" + specArray[2] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Drive:</td>\n" + 
			    		"                                    <td>" + specArray[3] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Engine:</td>\n" + 
			    		"                                    <td>" + specArray[4] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Cylinders:</td>\n" + 
			    		"                                    <td>" + specArray[5] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Horsepower:</td>\n" + 
			    		"                                    <td>" + specArray[6] + "</td>\n" + 
			    		"\n" + 
			    		"                                </tr>\n" + 
			    		"                                <tr>\n" + 
			    		"                                    <td>Torque:</td>\n" + 
			    		"                                    <td>" + specArray[7] + "</td>\n" + 
			    		"                                </tr>\n" + 
			    		"                            </table>\n" + 
			    		"                        </div>\n" + 
			    		"                        <div class=\"card-price\">\n" + 
			    		"                            <span class=\"font-regular left\">Price:</span>\n" + 
			    		"                            <span class=\"right\">" + desc.getPrice() + "</span>\n" + 
			    		"                        </div>\n" + 
			    		"                        <div class=\"card-button clear-float\">\n" + 
			    		"                            <a class=\"button small font-regular\" id=\"order-car-button\" onclick=\"addToCart();\">ADD TO CART</a>\n" + 
			    		"                        </div>\n" + 
			    		"                    </div>\n" + 
			    		"                </div>\n" + 
			    		"            </div>\n" + 
			    		"        </div></div>");
			    
			    out.print("<div class=\"overlay\" id=\"order-popup\">\n" + 
			    		"            <div class=\"order-form\">\n" + 
			    		"                <button class=\"close-button\" onclick=\"closeForm()\">&#10006</button>\n" + 
			    		"                <div class=\"container\">");
			    out.print("<div class=\"card order\">\n" + 
			    		"                            <div class=\"description-container center\">\n" + 
			    		"                                <div class=\"card-title\">" + desc.getYear() + " " + desc.getMake() + " " + desc.getModel() + " <br>SUCCESSFULLY ADDED TO CART</div>\n" + 
			    		"                                <div class=\"center\">"+
			    		"									<div class=\"card-button clear-float in-line\">\n" + 
			    		"			    		            <a class=\"button small font-regular\" id=\"order-car-button\" href=\"/CarsLand/cart\">GO TO CART</a>\n" + 
			    		"			    		            </div>\n" + "<div class=\"clear-float in-line\"> OR </div>"+
			    		"									<div class=\"card-button clear-float in-line\">\n" + 
			    		"			    		            <a class=\"button small font-regular\" id=\"order-car-button\" onclick=\"closeForm()\">CONTINUE SHOPPING</a>\n" + 
			    		"			    		            </div>\n" + 
			    										"</div>\n" + 
			    		"                            </div></div></div></div></div>"); 
			    
			    
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
	
}
