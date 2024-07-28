package com.lazyjack.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cars", schema = "car_fleet")
public class Car {
    @GeneratedValue
    @Id
    @Column(name = "car_id")
    private short carId;

    @Basic
    @Column(name = "car_brand")
    private String carBrand;

    @Basic
    @Column(name = "car_model")
    private String carModel;

    @Basic
    @Column(name = "car_license_plate")
    private String carLicensePlate;

    @Basic
    @Column(name = "car_description")
    private String description;

    @Basic
    @Column(name = "car_status", columnDefinition = "enum('available','in_use', 'maintenance')")
    private String carStatus;

    @Basic
    @Column(name = "occupied_from")
    private Timestamp occupiedFrom;

    @Basic
    @Column(name = "occupied_to")
    private Timestamp occupiedTo;

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "car_drivers",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id") }
    )

    private List<Driver> drivers = new ArrayList<>();

    public short getCarId() {
        return carId;
    }

    public void setCarId(short carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public Timestamp getOccupiedFrom() {
        return occupiedFrom;
    }

    public void setOccupiedFrom(Timestamp occupiedFrom) {
        this.occupiedFrom = occupiedFrom;
    }

    public Timestamp getOccupiedTo() {
        return occupiedTo;
    }

    public void setOccupiedTo(Timestamp occupiedTo) {
        this.occupiedTo = occupiedTo;
    }


}
