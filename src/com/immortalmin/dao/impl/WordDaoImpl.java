package com.immortalmin.dao.impl;

import com.immortalmin.dao.WordDao;
import com.immortalmin.pojo.word.*;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

public class WordDaoImpl extends BaseDao implements WordDao {

    @Override
    public List<OtherWord> getOtherWordList(int curPage, int pageSize) {
        String sql = "select wid,word_group as \"word_en\",C_meaning as \"word_ch\",source from words limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(OtherWord.class,sql);
    }

    @Override
    public List<KelinsiWord> getKelinsiWordList(int curPage, int pageSize) {
        String sql = "SELECT * FROM k_words limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(KelinsiWord.class,sql);
    }

    @Override
    public OtherSentence getOtherSentenceByWid(int wid) {
        String sql = "SELECT eid,wid,kid,word_meaning,SOURCE,E_sentence AS \"sentence_en\",C_translate AS \"sentence_ch\" FROM EXAMPLE WHERE wid=?;";
        return queryForOne(OtherSentence.class,sql,wid);
    }

    @Override
    public List<KelinsiSentence> getKelinsiSentencesByIid(int iid) {
        String sql = "SELECT * FROM k_sentences WHERE iid=?;";
        return queryForList(KelinsiSentence.class,sql,iid);
    }

    @Override
    public List<KelinsiItem> getKelinsiItemsByWid(int wid) {
        String sql = "SELECT * FROM k_items WHERE wid=?;";
        return queryForList(KelinsiItem.class,sql,wid);
    }


}
