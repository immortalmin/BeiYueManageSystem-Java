package com.immortalmin.dao;

import com.immortalmin.pojo.word.*;

import java.util.List;

public interface WordDao {

    /**
     * 查询恋练有词和由用户添加的单词
     */
    OtherWord getOtherWordByWid(int wid);

    /**
     * 查询柯林斯词典中的单词
     */
    KelinsiWord getKelinsiWordByWid(int wid);

    /**
     * 获取其他单词列表
     */
    List<OtherWord> getOtherWordList(int curPage, int pageSize);

    /**
     * 获取恋练有词单词列表
     */
    List<OtherWord> getLianlianWordList(int curPage, int pageSize);

    /**
     * 获取柯林斯单词列表
     */
    List<KelinsiWord> getKelinsiWordList(int curPage, int pageSize);

    /**
     * 获取其他例句的详细信息
     */
    List<OtherSentence> getOtherSentenceByWid(int wid);

    /**
     * 柯林斯词典    根据Item的iid获取Item中的所有例句
     */
    List<KelinsiSentence> getKelinsiSentencesByIid(int iid);

    /**
     * 柯林斯词典    根据wid获取单词的所有item
     */
    List<KelinsiItem> getKelinsiItemsByWid(int wid);

    /**
     * 获取单词的总数
     * @param dict_source 0:用户添加的单词 1:柯林斯词典
     */
    int getTotalCount(int dict_source);

    /**
     * 删除单词
     */
    void deleteWordByWid(int wid,int dict_source);

    /**
     * 新增单词
     */
    int insertWord(OtherWord otherWord);

    /**
     * 新增例句
     */
    int insertOtherSentence(OtherSentence otherSentence);
}
