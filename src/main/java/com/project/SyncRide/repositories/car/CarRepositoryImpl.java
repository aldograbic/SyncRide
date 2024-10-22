package com.project.SyncRide.repositories.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.car.Car;

@Repository
public class CarRepositoryImpl implements CarRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Car car) {
        String sql = "INSERT INTO cars (user_id, make, model, year_of_manufacture, color, seat_count, license_plate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getUserId(), car.getMake(), car.getModel(), car.getYearOfManufacture(), car.getColor(), car.getSeatCount(), car.getLicensePlate());
    }
}