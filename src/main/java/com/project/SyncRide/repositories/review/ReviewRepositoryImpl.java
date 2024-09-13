package com.project.SyncRide.repositories.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
