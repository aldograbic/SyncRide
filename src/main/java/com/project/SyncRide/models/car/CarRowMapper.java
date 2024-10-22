package com.project.SyncRide.models.car;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.SyncRide.models.user.User;
import com.project.SyncRide.repositories.user.UserRepository;

public class CarRowMapper implements RowMapper<Car> {

    private UserRepository userRepository;

    public CarRowMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {

        Car car = new Car();
        car.setCarId(rs.getInt("car_id"));
        car.setUserId(rs.getInt("user_id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYearOfManufacture(rs.getInt("year_of_manufacture"));
        car.setColor(rs.getString("color"));
        car.setSeatCount(rs.getInt("seat_count"));
        car.setLicensePlate(rs.getString("license_plate"));

        int userId = rs.getInt("user_id");
        User user = userRepository.findById(userId);
        car.setUser(user);

        return car;
    }
}
