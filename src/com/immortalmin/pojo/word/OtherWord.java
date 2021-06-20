package com.immortalmin.pojo.word;

import com.immortalmin.dao.UserDao;
import com.immortalmin.dao.impl.UserDaoImpl;

import java.util.List;

public class OtherWord extends Word{
    private String word_ch;
    private int source;
    private String source_username;
    private List<OtherSentence> sentences;

    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public List<OtherSentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<OtherSentence> sentences) {
        this.sentences = sentences;
    }

    public String getSource_username() {
        UserDao userDao = new UserDaoImpl();
        return userDao.queryUserByUid(source).getUsername();
    }

    public void setSource_username(String source_username) {
        this.source_username = source_username;
    }

    @Override
    public String toString() {
        return "OtherWord{" +
                "wid='" + getWid() + '\'' +
                "word_en='" + getWord_en() + '\'' +
                "word_ch='" + word_ch + '\'' +
                "sentences='" + sentences.toString() + '\'' +
                ", source=" + source +
                '}';
    }
}
