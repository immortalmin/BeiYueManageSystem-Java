package com.immortalmin.test;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;
import com.immortalmin.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("immortalmin2")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("immortalmin","7fe6fdf7bcef97ba5894da8c33c3f2a7")==null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("registerTest");
        user.setPwd("123");
        if(userDao.saveUser(user)!=-1){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
    }
}