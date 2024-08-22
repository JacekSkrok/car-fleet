package com.lazyjack.resource;

import com.lazyjack.model.Car;
import com.lazyjack.repository.CarRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;


@QuarkusTest
public class CarResourceTest {
    @InjectMock
    CarRepository carRepository;

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
}
