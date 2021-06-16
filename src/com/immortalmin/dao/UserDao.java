package com.immortalmin.dao;

import com.immortalmin.pojo.User;

public interface UserDao {


    /**
     * 查询用户是否已存在
     * @param username
     * @return
     */
    User queryUserByUsername(String username);

    User queryUserByUid(int uid);

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     * @return
     */
    User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 注册
     * @param user
     * @return
     */
    int saveUser(User user);

}