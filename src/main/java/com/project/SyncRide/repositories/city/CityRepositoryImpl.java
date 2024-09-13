package com.project.SyncRide.repositories.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepositoryImpl implements CityRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
