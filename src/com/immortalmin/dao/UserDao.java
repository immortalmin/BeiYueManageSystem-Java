package com.immortalmin.dao;

import com.immortalmin.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询用户是否已存在
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户id查询用户信息
     * @param uid
     * @return
     */
    User queryUserByUid(int uid);

    /**
     * 验证用户名和密码
     */
    User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 注册
     */
    int saveUser(User user);

    /**
     * 获取所有用户的信息
     */
    List<User> getAllUser(int curPage,int pageSize);

    /**
     * 获取用户数量
     */
    int getTotalCount();

    /**
     * 修改用户密码
     */
    void updatePwd(int uid,String newPwd);

}
