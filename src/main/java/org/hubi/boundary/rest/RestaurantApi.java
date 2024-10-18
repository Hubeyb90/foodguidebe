package org.hubi.boundary.rest;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hubi.entity.Restaurant;

@Path("/restaurant")
public interface RestaurantApi {

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    Response getRestaurants();

    @GET
    @Path("/{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    Response getRestaurantById(@PathParam("id") Long id);

    @POST
    @RolesAllowed({"admin", "guide", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addRestaurant(Restaurant restaurant);

    @PUT
    @RolesAllowed({"admin", "guide", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateRestaurant(Restaurant restaurant);

    @DELETE
    @RolesAllowed("admin")
    void deleteById(@QueryParam("id") Long id);
}
