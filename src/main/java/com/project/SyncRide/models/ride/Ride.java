package com.project.SyncRide.models.ride;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.models.user.User;

public class Ride {
    private int rideId;
    private int driverId;
    private int carId;
    private String startLocation;
    private String endLocation;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private LocalDateTime departureTime;
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
    public double getStartLatitude() {
        return startLatitude;
    }
    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }
    public double getStartLongitude() {
        return startLongitude;
    }
    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }
    public double getEndLatitude() {
        return endLatitude;
    }
    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }
    public double getEndLongitude() {
        return endLongitude;
    }
    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
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