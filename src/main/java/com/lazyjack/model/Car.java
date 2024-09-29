package com.lazyjack.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cars", schema = "car_fleet")
public class Car {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "car_id")
    private Long carId;

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
    private String carDescription;

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

    public Car() {}

    public Car(Long carId, String carBrand, String carModel) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public Car(String carBrand, String carModel, String carLicensePlate, String carStatus) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.carStatus = carStatus;
    }

    public Car(Long carId, String carBrand, String carModel, String carLicensePlate, String carStatus) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.carStatus = carStatus;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
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

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
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

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carLicensePlate='" + carLicensePlate + '\'' +
                ", carDescription='" + carDescription + '\'' +
                ", carStatus='" + carStatus + '\'' +
                ", occupiedFrom=" + occupiedFrom +
                ", occupiedTo=" + occupiedTo +
                ", drivers=" + drivers +
                '}';
    }

}
