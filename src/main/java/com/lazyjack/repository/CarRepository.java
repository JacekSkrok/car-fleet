package com.lazyjack.repository;

import com.lazyjack.model.Car;
import com.lazyjack.model.Car$;
import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class CarRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Car> getCar(short carId) {
        return jpaStreamer.stream(Car.class)
                .filter(Car$.carId.equal(carId))
                .findFirst();
    }
}
