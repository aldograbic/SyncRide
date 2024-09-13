package com.project.SyncRide.repositories.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
