package com.carsland.carslandrest;

import com.carsland.carslandrest.objects.Car;
import com.carsland.carslandrest.service.CarService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("car")
public class CarResource {
	
	@Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getCarById(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        System.out.println("In CarResource");
		//invokes the DB method which will fetch a todo_list item object by id
        Car car = CarService.getCarById(id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(car == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(car).build();
    }
	
	@GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllCars() {
        List<Car> carList = CarService.getAllCars();

        if(carList == null || carList.isEmpty()) {

        }

        return Response.ok(carList).build();
    }

}
