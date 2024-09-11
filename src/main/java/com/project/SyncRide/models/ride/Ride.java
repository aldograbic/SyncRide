package com.project.SyncRide.models.ride;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.models.user.User;

public class Ride {
    private int rideId;
    private int driverId;
    private int carId;
    private String startLocation;
    private String endLocation;
    private Timestamp departureTime;
    private int availableSeats;
    private BigDecimal pricePerPerson;
    private String additionalInfo;

    private User driver;
    private Car car;

    public int getRideId() {
        return rideId;
    }
    public void setRideId(int rideId) {
        this.rideId = rideId;
    }
    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public String getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }
    public String getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
    public Timestamp getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public BigDecimal getPricePerPerson() {
        return pricePerPerson;
    }
    public void setPricePerPerson(BigDecimal pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public User getDriver() {
        return driver;
    }
    public void setDriver(User driver) {
        this.driver = driver;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    } 
}