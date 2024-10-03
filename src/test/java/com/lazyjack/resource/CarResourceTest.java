package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.repository.CarRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@QuarkusTest
public class CarResourceTest {

    @InjectMock
    CarRepository carRepository;

    @Test
    public void testGetCarByIdFound() {
        long carId = 1;

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
        long carId = 999;

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

        List<Car> cars = Arrays.asList(new Car(1L, "BMW", "M6"), new Car(2L, "VW", "Tiguan"));

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

    @Test
    public void testCreateCar() {
        Car newCar = new Car("Honda", "Civic", "ABC127", "available");

        when(carRepository.save(any(Car.class))).thenReturn(new Car(1L, "Honda", "Civic", "ABC127", "available"));

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newCar)
        .when()
                .post("/cars/add")
        .then()
                .statusCode(201)
                .body("carId", equalTo(1))
                .body("carBrand", equalTo("Honda"))
                .body("carModel", equalTo("Civic"));
    }

    @Test
    public void updateCar() {
        Car existingCar = new Car(1L, "Honda", "Civic", "XYZ789", "available");

        Car updatedCar = new Car(1L, "Honda", "Civic", "XYZ789", "in_use");

        when(carRepository.getCar(existingCar.getCarId())).thenReturn(Optional.of(existingCar));
        when(carRepository.save(any(Car.class))).thenReturn(updatedCar);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedCar)
        .when()
                .put("/cars/1")
        .then()
                .statusCode(200)
                .body("carId", equalTo(1))
                .body("carBrand", equalTo("Honda"))
                .body("carModel", equalTo("Civic"))
                .body("carLicensePlate", equalTo("XYZ789"))
                .body("carStatus", equalTo("in_use"));
    }
}
