package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.repository.CarRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;


@QuarkusTest
public class CarResourceTest {

    @InjectMock
    CarRepository carRepository;

    @Inject
    CarResource carResource;

    @Test
    public void testGetCarByIdFound() {
        short carId = 1;

        Car car = new Car(carId, "Toyota", "Corolla");

        when(carRepository.getCar(carId)).thenReturn(Optional.of(car));

        given()
                .pathParam("carId", carId)
                .when()
                    .get("/cars/{carId}")
                        .then()
                        .statusCode(200)
                        .body("carId", equalTo(1));
    }

    @Test
    public void testGetCarByIdNotFound() {
        short carId = 999;

        given()
                .pathParam("carId", carId)
                .when()
                    .get("/cars/{carId}")
                        .then()
                        .statusCode(404)
                        .body(is("Car not found"));
    }

    @Test
    public void testGetAllCarsFound() {

        List<Car> cars = Arrays.asList(new Car((short) 1,"BMW", "M6"), new Car((short) 2, "VW", "Tiguan"));

        when(carRepository.listAll()).thenReturn(cars);

        given()
                .when()
                    .get("/cars")
                        .then()
                        .statusCode(200)
                        .body("$.size()", is(2));
    }

    @Test
    public void testGetAllCarsNotFound() {

        given()
                .when()
                    .get("/cars")
                        .then()
                        .statusCode(404)
                        .body(is("There are no cars"));
    }
}
