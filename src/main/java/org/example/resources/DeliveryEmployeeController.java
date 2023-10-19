package org.example.resources;

import io.swagger.annotations.Api;
import org.example.api.DeliveryEmployeeService;
import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeProjectRequest;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.ProjectException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

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
            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch(SQLException se){
            System.err.println(se.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
    @POST
    @Path("/addDeliveryEmployeeToProject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeliveryEmployeeToProject(List<DeliveryEmployeeProjectRequest> projects) {
        try{
            deliveryEmployeeService.assignDeliveryEmployeeToProject(projects);
            return Response.ok().build();
        }catch(SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
