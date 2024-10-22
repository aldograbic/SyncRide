package com.project.SyncRide.repositories.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public City getByCityId(int cityId) {
        String sql = "SELECT city_id, city_name FROM cities WHERE city_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                        City city = new City();
                        city.setCityId(rs.getInt("city_id"));
                        city.setCityName(rs.getString("city_name"));
                        return city;
                    }, cityId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}