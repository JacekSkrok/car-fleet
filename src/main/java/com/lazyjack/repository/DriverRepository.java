package com.lazyjack.repository;

import com.lazyjack.model.Driver;
import com.lazyjack.model.Driver$;
import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class DriverRepository implements PanacheRepository<Driver> {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Driver> getDriver(short driverId) {
        return jpaStreamer.stream(Driver.class)
                .filter(Driver$.driverId.equal(driverId))
                .findFirst();
    }
}
