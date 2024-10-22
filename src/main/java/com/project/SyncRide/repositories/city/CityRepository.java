package com.project.SyncRide.repositories.city;

import java.util.List;

import com.project.SyncRide.models.city.City;

public interface CityRepository {
     List<City> getAllCities();
     City getByCityId(int cityId);
}