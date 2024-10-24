package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.model.Driver;
import com.lazyjack.model.Driver$;
import com.lazyjack.repository.DriverRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/drivers")
public class DriverResource {

    @Inject
    DriverRepository driverRepository;

    @GET
    @Path("/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDriverById(short driverId) {
        Optional<Driver> driver = driverRepository.getDriver(driverId);
        if(driver.isPresent()) {
            return Response.ok(driver.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Driver not found")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDrivers() {
        List<Driver> drivers = driverRepository.listAll();
        if( !drivers.isEmpty()) {
            return Response.ok(drivers).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no drivers")
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDriver(Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return Response.status(Response.Status.CREATED)
                .entity(savedDriver)
                .build();
    }

    @PUT
    @Path("/{driverId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDriver(@PathParam("driverId") Long id, Driver driver) {
        Optional<Driver> existingDriver = driverRepository.getDriver(id);

        if( existingDriver.isEmpty() ) {
            Driver savedDriver =  driverRepository.save(driver);
            return Response.status(Response.Status.CREATED)
                    .entity(savedDriver)
                    .build();
        } else {
            existingDriver.get().setFirstName(driver.getFirstName());
            existingDriver.get().setLastName(driver.getLastName());
            existingDriver.get().setDriverDepartment(driver.getDriverDepartment());

            Driver updatedDriver = driverRepository.save(existingDriver.get());

            return Response.ok(updatedDriver).build();
        }
    }
 }
