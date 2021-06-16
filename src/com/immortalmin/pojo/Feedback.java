package com.immortalmin.pojo;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;
import com.immortalmin.service.UserService;
import com.immortalmin.service.impl.UserServiceImpl;

public class Feedback {
    int fid;
    int uid;
    String phone_model;
    String description;
    String contact;
    String progress;
    String img_path;
    String add_time;
    User user;
    int what;//0:´íÎó·´À¡  1:Òâ¼û

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public String getDescription() {
        return description.replace('\n',' ');
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public User getUser() {
        UserDao userDao = new UserDaoImpl();
        return userDao.queryUserByUid(uid);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", phone_model='" + phone_model + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", progress='" + progress + '\'' +
                ", img_path='" + img_path + '\'' +
                ", add_time='" + add_time + '\'' +
                ", what=" + what +
                '}';
    }
}
