package org.hubi.boundary.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.hubi.entity.Restaurant;

@Path("/restaurant")
@RegisterRestClient
public interface RestaurantApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getRestaurants();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getRestaurantById(@PathParam("id") Long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addRestaurant( Restaurant restaurant);

    @DELETE
    void deleteById(@QueryParam("id") Long id);
}
