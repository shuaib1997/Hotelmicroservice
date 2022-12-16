package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUser(String userId);
    List<User> getAllUsers();
    void deleteUser(String userId);
    User updateUser(User user);

}
