package com.carsland.carslandrest;

import com.carsland.carslandrest.objects.Zip;
import com.carsland.carslandrest.service.ZipService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("zip")
public class ZipResource {
	@Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getByZip(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        System.out.println("In CarResource");
		//invokes the DB method which will fetch a todo_list item object by id
        Zip zip = ZipService.getByZip(id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(zip == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(zip).build();
    }
}
