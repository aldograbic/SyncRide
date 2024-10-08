package com.project.SyncRide.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.SyncRide.models.ride.RideDTO;
import com.project.SyncRide.services.RideService;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/nearby")
    public List<RideDTO> getNearbyRides(@RequestParam double lat, @RequestParam double lng,
                                        @RequestParam(defaultValue = "5000") double maxDistance) {
        return rideService.findRidesNearLocation(lat, lng, maxDistance);  // maxDistance in meters
    }
}