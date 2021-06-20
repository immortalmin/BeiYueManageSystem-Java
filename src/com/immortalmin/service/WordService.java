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
}
