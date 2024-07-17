package com.lazyjack.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class CarResource {

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello to Quarkus";
    }

    @GET
    @Path("/helloJack")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloJack() {
        return "Hello Jack";
    }
}
