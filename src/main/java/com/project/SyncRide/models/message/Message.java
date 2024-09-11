package com.project.SyncRide.models.message;

import java.sql.Timestamp;

import com.project.SyncRide.models.ride.Ride;
import com.project.SyncRide.models.user.User;

public class Message {
    private int messageId;
    private int senderId;
    private int receiverId;
    private int rideId;
    private String content;
    private Timestamp createdAt;

    private User sender;
    private User receiver;
    private Ride ride;

    public int getMessageId() {
        return messageId;
    }
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    public int getRideId() {
        return rideId;
    }
    public void setRideId(int rideId) {
        this.rideId = rideId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    public Ride getRide() {
        return ride;
    }
    public void setRide(Ride ride) {
        this.ride = ride;
    }
}