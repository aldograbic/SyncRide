package com.project.SyncRide.repositories.ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RideRepositoryImpl implements RideRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}