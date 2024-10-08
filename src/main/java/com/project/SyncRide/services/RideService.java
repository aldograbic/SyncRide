package com.project.SyncRide.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SyncRide.models.ride.RideDTO;
import com.project.SyncRide.repositories.ride.RideRepository;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<RideDTO> findRidesNearLocation(double lat, double lng, double maxDistance) {
        return rideRepository.findNearbyRides(lat, lng, maxDistance);
    }
}