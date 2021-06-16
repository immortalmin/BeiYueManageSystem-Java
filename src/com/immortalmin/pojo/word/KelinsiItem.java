package com.immortalmin.pojo.word;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class KelinsiItem {
    private int iid;//id
    private String number;//item序号
    private String label;//标签
    private String word_ch;//单词的中文释义
    private String explanation;//单词的英文释义
    private String gram;//语法
    private String wid;//对应的单词id
    private List<String> en_tips = null;
    private List<KelinsiSentence> sentences = null;//例句

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public List<String> getEn_tips() {
        return en_tips;
    }

    public void setEn_tips(List<String> en_tips) {
        this.en_tips = en_tips;
    }

    public List<KelinsiSentence> getSentences() {
        WordDao wordDao = new WordDaoImpl();
        this.sentences = wordDao.getKelinsiSentencesByIid(this.iid);
        return sentences;
    }

    public void setSentences(List<KelinsiSentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "KelinsiItem{" +
                "iid='" + iid + '\'' +
                ", number='" + number + '\'' +
                ", label='" + label + '\'' +
                ", word_ch='" + word_ch + '\'' +
                ", explanation='" + getSentences().toString() + '\'' +
                ", gram='" + gram + '\'' +
                ", wid='" + wid + '\'' +
                ", en_tips=" + en_tips +
                ", sentences=" + sentences +
                '}';
    }
}
