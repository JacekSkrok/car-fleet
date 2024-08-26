package com.lazyjack.resource;

import com.lazyjack.model.Driver;
import com.lazyjack.repository.DriverRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DriverResourceTest {

    @InjectMock
    DriverRepository driverRepository;


    @Test
    public void testGetDriverByIdFound()
    {
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

}
