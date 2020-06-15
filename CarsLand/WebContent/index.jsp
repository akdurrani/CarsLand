<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.codehaus.jackson.map.ObjectMapper,
				org.codehaus.jackson.type.TypeReference, 
				org.glassfish.jersey.client.ClientConfig,
				javax.servlet.http.HttpServlet,
				javax.servlet.http.HttpServletRequest,
				javax.servlet.http.HttpServletResponse,
				javax.ws.rs.client.Client,
				javax.ws.rs.client.ClientBuilder,
				javax.ws.rs.client.WebTarget,
				javax.ws.rs.core.MediaType,
				javax.ws.rs.core.UriBuilder,
				java.io.IOException,
				java.io.PrintWriter,
				java.net.URI,
				java.util.List,
				javax.servlet.annotation.WebServlet,
				com.carsland.Car" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cars Land</title>
    <link rel="stylesheet" href="stylesheet/style.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap" rel="stylesheet">

</head>
<body>
<div id="home">
    <nav class="nav-bar home font-regular">
        <div class="logo">
            <a href="#home"> CARS | LAND</a>
        </div>
        <label for="toggle">&#9776</label>
        <input type="checkbox" id="toggle"/>

        <div class="nav-bar-items">
            <a href="#home">HOME</a>
            <a href="#cars">CARS FOR SALE</a>
            <a href="#about-us">ABOUT</a>
            <a href="#contact-us">CONTACT US</a>
            <a href="/CarsLand/cart" id="cart-logo">
            
            	<%
	            	/* response.setContentType("text/html");  */
	        		/* PrintWriter out = response.getWriter(); */
	        		 /* HttpSession session = request.getSession(true); */
	                 
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
            	%>
            
            </a>
        </div>
    </nav>
    <div class="container">
        <a class="button small font-regular" id="find-car-button" href="#cars">FIND YOUR CAR</a>
    </div>
</div>

<section id="learn-more">
    <div class="container">
        <h1 class="center">BUY USED CARS FOR AN <br> AFFORDABLE PRICE.</h1>
        <h2 class="center">SHOP ONLINE , GET IT DELIVERED TO YOU HASSLE-FREE</h2>
        <h3 class="center">Need more information ? We're happy to tell you!
            <a href="#about-us"><u>Learn More.</u></a>
        </h3>
    </div>
</section>

<section id="cars">
    <div class="container" id="section-title">
        <h3 class="center font-black">CARS FOR SALE</h3>
    </div>

    <div class="container center" id="car-list">
        <div class="row" id="car-list">
            <!-- add list of cars here -->
            
            <%
	            ClientConfig configr = new ClientConfig();
	
	            Client client = ClientBuilder.newClient(configr);
	
	            WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());
	
	
	            String jsonResponse =
	                    target.path("car").
	                            request(). //send a request
	                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	                            get(String.class); // use the get method and return the response as a string 
	    		/* PrintWriter out = response.getWriter(); */
	    		ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        		List<Car> carList = objectMapper.readValue(jsonResponse, new TypeReference<List<Car>>(){});
	    		
        		for(Car cars : carList) {
	    		    	out.print("<div class=\"col-3 card-space\">");
	    		    	out.print("<a href=\"product?id=" + cars.getId() + "\">");
	    		    	out.print("<div class=\"card effect\">");
	    		    	out.print("<div class=\"card-img\">");
	    		    	out.print("<img src=\"/CarsLand/images/" + cars.getImage() + "\" alt=\"\">");
	    		    	out.print("</div>");
	    		    	out.print("<div class=\"card-title\">" + cars.getYear() + " " + cars.getMake() + " " + cars.getModel() + "</div>");
	    		    	out.print("<div class=\"card-detail\">Color: " + cars.getColor() + " | " + cars.getMileage() + "</div>");
	    		    	out.print("<div class=\"card-price\">Price: " + cars.getPrice() + "</div>");
	    		    	out.print("</div>");
	                    out.print("</a>");
	                    out.print("</div>");
        		}
	    		    
	    		    //recently viewed
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
	                    		String idPath = "/" + id;
	                    		String viewedResponse =
	            	                    target.path("car").path(idPath).
	            	                            request(). //send a request
	            	                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	            	                            get(String.class); // use the get method and return the response as a string 
	            	            ObjectMapper objectMapper1 = new ObjectMapper();
	            	            Car carsViewed = objectMapper1.readValue(viewedResponse, Car.class);
	            	            	
        	                    	out.print("<div class=\"col-2 card-space center recent-views\">\n" + 
		    	             		    		"                    <a href=\"product?id=" + id + "\">\n" + 
		    	             		    		"                    <div class=\"card effect\">\n" + 
		    	             		    		"                        <div class=\"card-img\">\n" + 
		    	             		    		"                            <img src=\"images/"+ carsViewed.getImage() +"\" alt=\"\">\n" + 
		    	             		    		"                        </div>\n" + 
		    	             		    		"                        <div class=\"card-detail\">"+ carsViewed.getYear() + " " + carsViewed.getMake() + " " + carsViewed.getModel() +"</div>\n" + 
		    	             		    		"                    </div>\n" + 
		    	             		    		"                    </a>\n" + 
		    	             		    		"                </div>");
	            	            
	    	                	
	                    	}
	                    	out.print("</div></div>");
	                    }
	                    
	                } 
	    		    
	    		    		
	    		 
			%>
            
        </div>
    </div>
</section>

<section id="about-us">
    <div class="row">
        <div class="col-sm-12 col-6 float-left" id="tesla"></div>
        <div class="col-sm-12 col-6 float-right" id="business-overview">
            <div class="container">
                <h1 class="center">WE <u>ONLY</u> SELL <br> THE BEST USED CARS</h1>
                <div class="container">
                    <h3 class="font-light center">Our cars are certified used from the top brands around
                        the world sold at affordable prices. We carefully select
                        the cars we sell to our customers with a detailed car history
                        reports.
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-6 float-left" id="shipping-overview">
            <div class="container">
                <h1 class="center">HASSLE-FREE <br> 7-DAY DELIVERY</h1>
                <h3 class="center font-light">Shipping and Handling Fees Apply</h3>
            </div>
        </div>
        <div class="col-sm-12 col-6 float-right" id="toyota"></div>
    </div>
</section>

<section id="contact-us" class="clear-float">
    <div class="container center">
        <h6 class="font-regular font-black">The Management Team</h6>
        <!-- <div class="team">
            <img class="team-img" src="images/ian.png">
            <h2 class="font-black font-regular">John Ian David </h2>
            <p class="font-light font-black">ID# 62398773</p>
        </div> -->
        <div class="team">
            <img class="team-img" src="images/azmeh.jpeg">
            <h2 class="font-black font-regular">Azmeh Durrani</h2>
            <p class="font-light font-black">ID# 70173398</p>
        </div>
    </div>
</section>

<footer>
    <div class="logo clear-float">
        <a href="#home"> CARS | LAND</a>
    </div>
    <div class="credit">
        <a href="credits.html">Content Credits</a>
    </div>
</footer>
</body>
<script src="javascript/carsland.js"></script>
</html>