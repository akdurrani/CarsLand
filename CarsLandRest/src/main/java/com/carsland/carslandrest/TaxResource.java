package com.carsland.carslandrest;

import com.carsland.carslandrest.objects.Tax;
import com.carsland.carslandrest.service.TaxService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("tax")
public class TaxResource {
	@Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getTacrByZip(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
        System.out.println("In CarResource");
		//invokes the DB method which will fetch a todo_list item object by id
        Tax tax = TaxService.getTaxByZip(id);

        //Respond with a 404 if there is no such todo_list item for the id provided
        if(tax == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //Respond with a 200 OK if you have a todo_list_item object to return as response
        return Response.ok(tax).build();
    }
}
