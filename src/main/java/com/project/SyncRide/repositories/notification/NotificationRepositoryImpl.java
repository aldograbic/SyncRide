package com.project.SyncRide.repositories.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
