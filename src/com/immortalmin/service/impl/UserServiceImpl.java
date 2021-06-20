package com.immortalmin.service.impl;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;
import com.immortalmin.pojo.User;
import com.immortalmin.service.UserService;
import com.immortalmin.utils.MD5Utils;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), MD5Utils.string2MD5(user.getPwd()));
    }

    @Override
    public boolean isUsernameExists(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUserList(int curPage,int pageSize) {
        return userDao.getAllUser(curPage, pageSize);
    }
}
