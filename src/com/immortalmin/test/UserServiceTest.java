package com.immortalmin.test;

import com.immortalmin.pojo.User;
import com.immortalmin.service.UserService;
import com.immortalmin.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        User user = new User();
        user.setUsername("serviceTest");
        user.setPwd("123");
        userService.registerUser(user);
    }

    @Test
    public void login() {
        User user = new User();
        user.setUsername("immortalmin");
        user.setPwd("7fe6fdf7bcef97ba5894da8c33c3f2a7");
        System.out.println(userService.login(user));
    }

    @Test
    public void isUsernameExists() {
        System.out.println(userService.isUsernameExists("immortalmin"));
    }
}