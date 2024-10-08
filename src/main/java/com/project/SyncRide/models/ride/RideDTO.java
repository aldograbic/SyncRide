package com.project.SyncRide.models.ride;

import java.math.BigDecimal;

public class RideDTO {
    private Long rideId;
    private String startLocation;
    private String endLocation;
    private double startLatitude;
    private double startLongitude;
    private int availableSeats;
    private BigDecimal pricePerPerson;

    public RideDTO(Long rideId, String startLocation, String endLocation, double startLatitude, double startLongitude, int availableSeats, BigDecimal pricePerPerson) {
        this.rideId = rideId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.availableSeats = availableSeats;
        this.pricePerPerson = pricePerPerson;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
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
}