package com.carsland;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

//@WebServlet("/state")
public class GetState extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
ClientConfig configr = new ClientConfig();
		
        Client client = ClientBuilder.newClient(configr);

        WebTarget target = client.target(UriBuilder.fromUri("http://localhost:8080/CarsLandRest").build());
//
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		
		String jsonResponse =
                target.path("zip").path("" + id).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string 
		
		ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

		Zip zip;
		
		zip = objectMapper.readValue(jsonResponse, Zip.class);
		    while (zip != null) {
		    	out.print(zip.getState());
		    	
		    }
	}
}
