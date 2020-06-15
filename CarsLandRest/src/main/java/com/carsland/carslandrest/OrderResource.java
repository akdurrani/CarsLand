package com.carsland.carslandrest;

import com.carsland.carslandrest.objects.Order;
import com.carsland.carslandrest.service.OrderService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("order")
public class OrderResource {

	@Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getOrderById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        System.out.println("In CarResource");
		//invokes the DB method which will fetch a todo_list item object by id
        Order order = OrderService.getOrderById(id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(order).build();
    }
	
	@GET
    @Produces( { MediaType.APPLICATION_JSON })
	public Response getLastOrder() {
		//invokes the DB method which will fetch a todo_list item object by id
        Order order = OrderService.getLastOrder();

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(order).build();
    }
//    public Response getAllOrders() {
//        List<Order> orderList = OrderService.getAllOrders();
//
//        if(orderList == null || orderList.isEmpty()) {
//
//        }
//
//        return Response.ok(orderList).build();
//    }
//	
	@POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addOrder(Order order) {
		System.out.println("Inserting records from resource...");
        //The todo object here is automatically constructed from the json request. Jersey is so cool!
        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("Order Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
