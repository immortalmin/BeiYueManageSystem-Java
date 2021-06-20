package com.immortalmin.service;

import com.immortalmin.pojo.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    User login(User user);

    boolean isUsernameExists(String username);

    List<User> getUserList(int curPage,int pageSize);

}
