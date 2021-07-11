package com.immortalmin.service;

import com.immortalmin.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 用户注册
     */
    void registerUser(User user);

    /**
     * 用户登录
     */
    User login(User user);

    /**
     * 判断用户名是否已存在
     */
    boolean isUsernameExists(String username);

    /**
     * 获取用户列表
     */
    List<User> getUserList(int curPage,int pageSize);

}
