package com.lazyjack.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="drivers", schema = "car_fleet")
public class Driver {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "driver_id")
    private Long driverId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "driver_department")
    private String driverDepartment;

    @ManyToMany(mappedBy ="drivers", fetch = FetchType.EAGER)
    private Set<Car> cars = new HashSet<>();

    public Driver() {}

    public Driver(Long driverId, String firstName, String lastName) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDriverDepartment() {
        return driverDepartment;
    }

    public void setDriverDepartment(String driverDepartment) {
        this.driverDepartment = driverDepartment;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
