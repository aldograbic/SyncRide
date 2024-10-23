package com.project.SyncRide.repositories.user;

import com.project.SyncRide.models.user.User;

public interface UserRepository {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findByConfirmationToken(String token);
    void save(User user);
    void update(User user);
    void updateVerification(User user);
    void saveFull(User user);
    User findById(int userId);
   
}