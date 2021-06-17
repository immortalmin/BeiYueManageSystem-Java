package com.immortalmin.pojo.word;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;

public class OtherWord extends Word{
    private String word_ch;
    private String source;

    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public String getSource() {
        UserDao userDao = new UserDaoImpl();
        return userDao.queryUserByUid(Integer.parseInt(source)).getUsername();
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "OtherWord{" +
                "wid='" + getWid() + '\'' +
                "word_en='" + getWord_en() + '\'' +
                "word_ch='" + word_ch + '\'' +
                ", source=" + source +
                '}';
    }
}
