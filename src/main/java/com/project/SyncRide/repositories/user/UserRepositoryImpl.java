package com.project.SyncRide.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}