package com.immortalmin.dao;

import com.immortalmin.pojo.word.*;

import java.util.List;

public interface WordDao {

    /**
     * 获取其他单词列表
     * @param curPage
     * @param pageSize
     * @return
     */
    List<OtherWord> getOtherWordList(int curPage, int pageSize);

    /**
     * 获取柯林斯单词列表
     * @param curPage
     * @param pageSize
     * @return
     */
    List<KelinsiWord> getKelinsiWordList(int curPage, int pageSize);

    /**
     * 获取其他例句的详细信息
     * @param wid
     * @return
     */
    OtherSentence getOtherSentenceByWid(int wid);

    List<KelinsiSentence> getKelinsiSentencesByIid(int iid);

    List<KelinsiItem> getKelinsiItemsByWid(int wid);
}
