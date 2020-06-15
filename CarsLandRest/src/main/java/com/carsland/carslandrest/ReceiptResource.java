package com.carsland.carslandrest;

import com.carsland.carslandrest.objects.Receipt;
import com.carsland.carslandrest.service.ReceiptService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("receipt")
public class ReceiptResource {
	
	@Path("{id}")
	@GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllReceipts(@PathParam("id") int id) {
        List<Receipt> receiptList = ReceiptService.getAllReceipts(id);

        if(receiptList == null || receiptList.isEmpty()) {

        }

        return Response.ok(receiptList).build();
    }
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON}) //This method accepts a request of the JSON type
    public Response addReceipt(Receipt receipt) {
		System.out.println("Inserting records from resource...");
        //The todo object here is automatically constructed from the json request. Jersey is so cool!
        if(ReceiptService.AddReceipt(receipt)) {
            return Response.ok().entity("Receipt Added Successfully").build();
        }

        // Return an Internal Server error because something wrong happened. This should never be executed
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
