package com.project.SyncRide.models.booking;

import java.sql.Timestamp;

import com.project.SyncRide.models.ride.Ride;
import com.project.SyncRide.models.user.User;

public class Booking {
    private int bookingId;
    private int passengerId;
    private int rideId;
    private Timestamp bookingDate;
    private String status;

    private User passenger;
    private Ride ride;

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
    public int getRideId() {
        return rideId;
    }
    public void setRideId(int rideId) {
        this.rideId = rideId;
    }
    public Timestamp getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public User getPassenger() {
        return passenger;
    }
    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }
    public Ride getRide() {
        return ride;
    }
    public void setRide(Ride ride) {
        this.ride = ride;
    }
}