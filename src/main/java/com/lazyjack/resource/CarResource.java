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
    public Response getCarById(@PathParam("carId") Long carId) {
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

    @POST
    @Path("/{carId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(Car car) {
        Car savedCar = carRepository.save(car);
        return Response.status(Response.Status.CREATED)
                .entity(savedCar)
                .build();
    }

    @PUT
    @Path("/{carId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("carId") Long id, Car car) {

        Optional<Car> existingCar = carRepository.getCar(id);

        if( existingCar.isEmpty() ) {
            Car savedCar = carRepository.save(car);
            return Response.status(Response.Status.CREATED)
                    .entity(savedCar)
                    .build();
        } else {

            existingCar.get().setCarBrand(car.getCarBrand());
            existingCar.get().setCarModel(car.getCarModel());
            existingCar.get().setCarLicensePlate(car.getCarLicensePlate());
            existingCar.get().setCarDescription(car.getCarDescription());
            existingCar.get().setCarStatus(car.getCarStatus());
            existingCar.get().setOccupiedFrom(car.getOccupiedFrom());
            existingCar.get().setOccupiedTo(car.getOccupiedTo());

            Car updatedCar = carRepository.save(existingCar.get());

            return Response.ok(updatedCar).build();
        }
    }

    @DELETE
    @Path("/{carId}")
    public Response deleteCar(@PathParam("carId") Long id) {
        Optional<Car> toDeleteCar = carRepository.getCar(id);

        if (toDeleteCar.isPresent()) {
            carRepository.deleteById(toDeleteCar.get().getCarId());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Car not found")
                    .build();
        }

    }




}
