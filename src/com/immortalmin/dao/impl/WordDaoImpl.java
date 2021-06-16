package com.immortalmin.dao.impl;

import com.immortalmin.dao.WordDao;
import com.immortalmin.pojo.word.Word;

import java.util.List;

public class WordDaoImpl extends BaseDao implements WordDao {

    @Override
    public List<Word> getWordList(int curPage, int pageSize, int dict_source) {
        if(dict_source==0){
            String sql = "";
        }
        return null;
    }

    @Override
    public Word getWordByIdAndSource(int wid, int dict_source) {
        return null;
    }
}
