package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.model.Driver;
import com.lazyjack.repository.DriverRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DriverResourceTest {

    @InjectMock
    DriverRepository driverRepository;

    @Test
    public void testGetDriverByIdFound() {
        Long driverId = 1L;

        Driver driver = new Driver(driverId, "Jan", "Nowak");

        when(driverRepository.getDriver(driverId)).thenReturn(Optional.of(driver));

        given()
                .pathParam("driverId", driverId)
                .when()
                    .get("drivers/{driverId}")
                        .then()
                        .statusCode(200)
                        .body("driverId", equalTo(1));
    }

    @Test
    public  void testGetDriverByIdNotFound() {
        short driverId = 999;

        given()
                .pathParam("driverId", driverId)
                .when()
                    .get("/drivers/{driverId}")
                        .then()
                        .statusCode(404)
                        .body(is("Driver not found"));
    }

    @Test
    public void testGetAllCarsFound() {
        List<Driver> drivers = Arrays.asList(new Driver( 1L, "Jan", "Nowak"), new Driver( 2L, "Adam", "Kowalski"));

        when(driverRepository.listAll()).thenReturn(drivers);

        given()
                .when()
                    .get("/drivers")
                        .then()
                        .statusCode(200)
                        .body("$.size()", CoreMatchers.is(2));
    }

    @Test
    public void testGetAllDriversNotFound() {

        given()
                .when()
                    .get("/drivers")
                        .then()
                        .statusCode(404)
                        .body(CoreMatchers.is("There are no drivers"));
    }

    @Test
    public void testCreateDriver() {
        Driver newDriver = new Driver(1L,"Jan", "Nowak");

        when(driverRepository.save(any(Driver.class))).thenReturn(new Driver(1L, "Jan", "Nowak"));

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newDriver)
        .when()
                .post("/drivers/add")
        .then()
                .statusCode(201)
                .body("driverId", equalTo(1))
                .body("firstName", equalTo("Jan"))
                .body("lastName", equalTo("Nowak"));
    }

    @Test
    public void updateDriver() {}

    @Test
    public void deleteDriver() {}


}
