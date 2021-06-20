package com.immortalmin.pojo.word;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;

public class OtherSentence extends ExampleSentence{
    private int eid;//例句id
    private int wid;//对应单词的id
    private int kid;//对应柯林斯词典中单词的id
    private String word_meaning;//单词在例句中的释义
    private int source;//添加该例句的用户id
    private String source_username;//添加该例句的用户名

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getWord_meaning() {
        return word_meaning;
    }

    public void setWord_meaning(String word_meaning) {
        this.word_meaning = word_meaning;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getSource_username() {
        UserDao userDao = new UserDaoImpl();
        return userDao.queryUserByUid(source).getUsername();
    }

    public void setSource_username(String source_username) {
        this.source_username = source_username;
    }

    public String toString() {
        return "OtherSentence{" +
                "eid='" + eid + '\'' +
                ", sentence_en='" + getSentence_en() + '\'' +
                ", sentence_ch='" + getSentence_ch() + '\'' +
                ", wid='" + wid + '\'' +
                ", kid='" + kid + '\'' +
                ", word_meaning='" + word_meaning + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
