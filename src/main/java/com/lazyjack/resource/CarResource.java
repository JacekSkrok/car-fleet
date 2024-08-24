package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.repository.CarRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/cars")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @GET
    @Path("/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarById(@PathParam("carId") short carId) {
        Optional<Car> car = carRepository.getCar(carId);

        if (car.isPresent()) {
            return Response.ok(car.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        List<Car> cars = carRepository.listAll();
        if (!cars.isEmpty()) {
            return Response.ok(cars).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no cars")
                    .build();
        }
    }
}
