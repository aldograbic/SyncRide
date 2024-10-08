package com.project.SyncRide.repositories.ride;

import java.util.List;

import com.project.SyncRide.models.ride.RideDTO;

public interface RideRepository {
    List<RideDTO> findNearbyRides(double lat, double lng, double maxDistance);
}