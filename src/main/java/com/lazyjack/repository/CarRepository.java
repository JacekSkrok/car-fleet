package com.lazyjack.repository;

import com.lazyjack.model.Car;
import com.lazyjack.model.Car$;
import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Car> getCar(Long carId) {
        return jpaStreamer.stream(Car.class)
                .filter(Car$.carId.equal(carId))
                .findFirst();
    }

    @Transactional
    public Car save(Car car) {
        getEntityManager().persist(car);
        return car;
    }
}
