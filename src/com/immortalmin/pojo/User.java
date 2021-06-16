package com.immortalmin.pojo;

public class User {
    private int uid;
    private String open_id;
    private int login_mode;
    private String username;
    private String pwd;
    private String profile_photo;
    private String telephone;
    private String email;
    private String motto;
    private long last_login;

    public User() {
    }

    public User(int uid, String open_id, int login_mode, String username, String pwd, String profile_photo, String telephone, String email, String motto, long last_login) {
        this.uid = uid;
        this.open_id = open_id;
        this.login_mode = login_mode;
        this.username = username;
        this.pwd = pwd;
        this.profile_photo = profile_photo;
        this.telephone = telephone;
        this.email = email;
        this.motto = motto;
        this.last_login = last_login;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getLogin_mode() {
        return login_mode;
    }

    public void setLogin_mode(int login_mode) {
        this.login_mode = login_mode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public long getLast_login() {
        return last_login;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", open_id='" + open_id + '\'' +
                ", login_mode=" + login_mode +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", motto='" + motto + '\'' +
                ", last_login=" + last_login +
                '}';
    }
}
