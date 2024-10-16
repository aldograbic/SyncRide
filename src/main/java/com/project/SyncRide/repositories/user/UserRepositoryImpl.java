package com.project.SyncRide.repositories.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.SyncRide.models.user.User;
import com.project.SyncRide.models.user.UserRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT user_id, email, full_name, password, gender, address, phone, bio, profile_picture, role, city_id, created_at, confirmation_token, email_verified FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), email);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public User findByConfirmationToken(String token) {
        String sql = "SELECT user_id, email, full_name, password, gender, address, phone, bio, profile_picture, role, city_id, created_at, confirmation_token, email_verified FROM users WHERE confirmation_token = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), token);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (email, confirmation_token) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getConfirmationToken());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET full_name = ?, password = ?, phone = ?, gender = ?, address = ?, city_id = ?, bio = ?, profile_picture = ?";
        jdbcTemplate.update(sql, user.getFullName(), user.getPassword(), user.getPhone(), user.getGender(), user.getAddress(), user.getCityId(), user.getBio(), user.getProfilePicture());
    }

    @Override
    public void updateVerification(User user) {
        String sql = "UPDATE users SET email_verified = ?";
        jdbcTemplate.update(sql, user.isEmailVerified());
    }

    @Override
    public void saveFull(User user) {
        String sql = "UPDATE users SET full_name = ?, password = ?, gender = ?, phone = ?, address = ?, city_id = ?, bio = ?, profile_picture = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getFullName(), user.getPassword(), user.getGender(), user.getPhone(), user.getAddress(), user.getCityId(), user.getBio(), user.getProfilePicture(), user.getUserId());
    }

    @Override
    public User findById(Long userId) {
        String sql = "SELECT user_id, email, full_name, password, gender, address, phone, bio, profile_picture, role, city_id, created_at, confirmation_token, email_verified FROM users WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }
}