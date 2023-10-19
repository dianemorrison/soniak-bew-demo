package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.DeliveryEmployeeService;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.ProjectException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Delivery Employee API")
@Path("/api")
public class DeliveryEmployeeController {
    DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();
    @GET
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        try {
            return Response.ok(deliveryEmployeeService.getAllDeliveryEmployees()).build();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
    @POST
    @Path("/deliveryEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest employee){
        try{
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(employee)).build();
        }catch(ProjectException e){
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }

    }
}
