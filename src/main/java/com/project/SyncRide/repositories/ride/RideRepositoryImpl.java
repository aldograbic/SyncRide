package com.project.SyncRide.repositories.ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.ride.Ride;

@Repository
public class RideRepositoryImpl implements RideRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertRide(Ride ride) {
        String sql = "INSERT INTO rides (driver_id, car_id, start_location, end_location, departure_time, " +
                     "start_latitude, start_longitude, end_latitude, end_longitude, available_seats, " +
                     "price_per_person, additional_info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql, ride.getDriverId(), ride.getCarId(), ride.getStartLocation(), ride.getEndLocation(),
                            ride.getDepartureTime(), ride.getStartLatitude(), ride.getStartLongitude(),
                            ride.getEndLatitude(), ride.getEndLongitude(), ride.getAvailableSeats(),
                            ride.getPricePerPerson(), ride.getAdditionalInfo());
    }
}