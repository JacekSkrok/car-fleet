package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.model.Driver;
import com.lazyjack.repository.DriverRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DriverResourceTest {

    @InjectMock
    DriverRepository driverRepository;

    @Test
    public void testGetDriverByIdFound() {
        short driverId = 1;

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
        List<Driver> drivers = Arrays.asList(new Driver((short) 1, "Jan", "Nowak"), new Driver((short) 2, "Adam", "Kowalski"));

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



}
