package com.project.SyncRide.repositories.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepositoryImpl implements BookingRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}