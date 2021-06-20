package com.immortalmin.dao.impl;

import com.immortalmin.dao.UserDao;
import com.immortalmin.pojo.User;
import com.immortalmin.utils.MD5Utils;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUid(int uid) {
        String sql = "select * from user where uid=?";
        return queryForOne(User.class,sql,uid);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String pwd) {
        String sql = "select * from user where username=? and pwd=?";
        return queryForOne(User.class,sql,username,pwd);
    }

    @Override
    public int saveUser(User user) {
        long currentTime = System.currentTimeMillis();//获取当前时间戳
        String sql = "insert into user(username,pwd,last_login)values(?,?,?)";
        //TODO: 1:setting;2:用户头像
//        if(update(sql,user.getUsername(),user.getPwd(),currentTime)!=-1) {
//            sql = "insert into setting(uid)value(?)";
//        }
        return update(sql,user.getUsername(),user.getPwd(),currentTime);
    }

    @Override
    public List<User> getAllUser(int curPage,int pageSize) {
        String sql = "select * from user limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(User.class,sql);
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from user";
        return Integer.parseInt(queryForSingleValue(sql).toString());
    }

    @Override
    public void updatePwd(int uid, String newPwd) {
        String sql = "update user set pwd=? where uid=?";
        System.out.println(MD5Utils.string2MD5(newPwd));
        update(sql,MD5Utils.string2MD5(newPwd),uid);
    }
}
