package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.repository.CarRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello to Quarkus";
    }

    @GET
    @Path("/car/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCarById(short carId) {
        Optional<Car> car = carRepository.getCar(carId);

        if (car.isPresent()) {
            return Response.ok(car.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Car not fount")
                    .build();
        }
    }
}
