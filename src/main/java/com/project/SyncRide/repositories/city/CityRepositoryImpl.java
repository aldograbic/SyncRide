package com.project.SyncRide.repositories.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.city.City;

@Repository
public class CityRepositoryImpl implements CityRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> getAllCities() {
        String sql = "SELECT city_id, city_name FROM cities";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(City.class));
    }
}