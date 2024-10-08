package com.project.SyncRide.repositories.ride;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.ride.RideDTO;

@Repository
public class RideRepositoryImpl implements RideRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RideDTO> findNearbyRides(double lat, double lng, double maxDistance) {
        String sql = "SELECT ride_id, start_location, end_location, start_latitude, start_longitude, " +
                     "available_seats, price_per_person, " +
                     "ST_Distance_Sphere(point(start_longitude, start_latitude), point(?, ?)) AS distance " +
                     "FROM rides " +
                     "WHERE ST_Distance_Sphere(point(start_longitude, start_latitude), point(?, ?)) < ? " +
                     "ORDER BY distance ASC";

        return jdbcTemplate.query(sql, new Object[]{lng, lat, lng, lat, maxDistance},
            (rs, rowNum) -> new RideDTO(
                rs.getLong("ride_id"),
                rs.getString("start_location"),
                rs.getString("end_location"),
                rs.getDouble("start_latitude"),
                rs.getDouble("start_longitude"),
                rs.getInt("available_seats"),
                rs.getBigDecimal("price_per_person")
            )
        );
    }
}