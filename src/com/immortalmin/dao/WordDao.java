package com.immortalmin.dao;

import com.immortalmin.pojo.word.Word;

import java.util.List;

public interface WordDao {

    /**
     * 获取单词列表
     * @param curPage
     * @param pageSize
     * @param dict_source 0:其他单词  1:柯林斯
     * @return
     */
    List<Word> getWordList(int curPage,int pageSize,int dict_source);

    /**
     * 获取单词的详细信息
     * @param wid
     * @param dict_source
     * @return
     */
    Word getWordByIdAndSource(int wid,int dict_source);
}
