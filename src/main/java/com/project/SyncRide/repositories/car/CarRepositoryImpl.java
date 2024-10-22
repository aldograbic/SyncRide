package com.project.SyncRide.repositories.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.car.Car;
import com.project.SyncRide.models.car.CarRowMapper;
import com.project.SyncRide.repositories.user.UserRepository;

import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(Car car) {
        String sql = "INSERT INTO user_cars (user_id, make, model, year_of_manufacture, color, seat_count, license_plate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getUserId(), car.getMake(), car.getModel(), car.getYearOfManufacture(), car.getColor(), car.getSeatCount(), car.getLicensePlate());
    }

    @Override
    public List<Car> findAllByUserId(int userId) {
        String sql = "SELECT * FROM user_cars WHERE user_id = ?";
        return jdbcTemplate.query(sql, new CarRowMapper(userRepository), userId);
    }

    @Override
    public Car findByCarId(int carId) {
        String sql = "SELECT * FROM user_cars WHERE car_id = ?";
        return jdbcTemplate.queryForObject(sql, new CarRowMapper(userRepository), carId);
    }

    @Override
    public void update(Car car) {
        String sql = "UPDATE user_cars SET make = ?, model = ?, year_of_manufacture = ?, color = ?, seat_count = ?, license_plate = ? WHERE car_id = ?";
        jdbcTemplate.update(sql, car.getMake(), car.getModel(), car.getYearOfManufacture(), car.getColor(), car.getSeatCount(), car.getLicensePlate(), car.getCarId());
    }

    @Override
    public void deleteByCarId(int carId) {
        String sql = "DELETE FROM user_cars WHERE car_id = ?";
        jdbcTemplate.update(sql, carId);
    }
}