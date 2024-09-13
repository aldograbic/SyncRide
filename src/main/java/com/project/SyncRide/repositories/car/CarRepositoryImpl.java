package com.project.SyncRide.repositories.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl implements CarRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
