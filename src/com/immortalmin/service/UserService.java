package com.immortalmin.service;

import com.immortalmin.pojo.User;

public interface UserService {


    void registerUser(User user);

    User login(User user);

    boolean isUsernameExists(String username);
}
