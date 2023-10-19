package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.DeliveryEmployeeService;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.DeliveryEmployeeDoesNotExistException;
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

    @GET
    @Path("/deliveryEmployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id) {
        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeById(id)).build();
        } catch(DeliveryEmployeeDoesNotExistException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch(SQLException se){
            System.err.println(se.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch(SQLException se){
            System.err.println(se.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
