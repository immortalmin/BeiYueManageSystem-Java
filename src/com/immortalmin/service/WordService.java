package com.immortalmin.service;

import com.immortalmin.pojo.word.OtherWord;

import javax.servlet.ServletContext;
import java.util.List;

public interface WordService {

    /**
     * 从文件中整理单词
     */
    List<OtherWord> analysisWordString(ServletContext context, int uid);

    /**
     * 将单词和例句导入数据库
     */
    void importWord(List<OtherWord> wordList);

    /**
     * 获取单词列表
     * @param dict_source 0:用户添加的单词     1：恋练有词      2：柯林斯词典
     */
    <T> List<T> getWordList(Class<T> type,int curPage,int pageSize,int dict_source);

    /**
     * 获取单词详情
     * @param dict_source 0:用户添加的单词     1：恋练有词      2：柯林斯词典
     */
    <T> T getWordDetail(Class<T> type,int wid,int dict_source);

    /**
     * 获取例句
     * @param dict_source 0:用户添加的单词     1：恋练有词      2：柯林斯词典
     */
    <T> List<T> getSentences(Class<T> type,int wid,int dict_source);
}
