package com.project.SyncRide.repositories.car;

import java.util.List;

import com.project.SyncRide.models.car.Car;

public interface CarRepository {
    
    void insert(Car car);
    List<Car> findAllByUserId(int userId);
    Car findByCarId(int carId);
    void update(Car car);
    void deleteByCarId(int carId);
}