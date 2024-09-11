package com.project.SyncRide.models.review;

import java.sql.Timestamp;

import com.project.SyncRide.models.user.User;

public class Review {
    private int reviewId;
    private int reviewerId;
    private int driverId;
    private int rating;
    private String comment;
    private Timestamp createdAt;

    private User reviewer;
    private User driver;

    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public int getReviewerId() {
        return reviewerId;
    }
    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }
    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public User getReviewer() {
        return reviewer;
    }
    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
    public User getDriver() {
        return driver;
    }
    public void setDriver(User driver) {
        this.driver = driver;
    }
}