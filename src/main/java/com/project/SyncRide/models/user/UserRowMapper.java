package com.project.SyncRide.models.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>{
    
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getString("phone"));
        user.setGender(rs.getString("gender"));
        user.setBio(rs.getString("bio"));
        user.setProfilePicture(rs.getString("profile_picture"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCityId(rs.getInt("city_id"));
        user.setRole(rs.getString("role"));
        user.setEmailVerified(rs.getBoolean("email_verified"));
        user.setConfirmationToken(rs.getString("confirmation_token"));
        user.setCreatedAt(rs.getTimestamp("created_at"));

        return user;
    }
}