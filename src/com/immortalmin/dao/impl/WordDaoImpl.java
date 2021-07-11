package com.immortalmin.dao.impl;

import com.immortalmin.dao.WordDao;
import com.immortalmin.pojo.word.*;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

public class WordDaoImpl extends BaseDao implements WordDao {


    @Override
    public OtherWord getOtherWordByWid(int wid) {
        String sql = "SELECT wid,word_group AS \"word_en\",C_meaning AS \"word_ch\",source FROM words WHERE wid=?;";
        return queryForOne(OtherWord.class,sql,wid);
    }

    @Override
    public KelinsiWord getKelinsiWordByWid(int wid) {
        String sql = "SELECT * FROM k_words WHERE wid=?;";
        return queryForOne(KelinsiWord.class,sql,wid);
    }

    @Override
    public List<OtherWord> getOtherWordList(int curPage, int pageSize) {
        String sql = "select wid,word_group as \"word_en\",C_meaning as \"word_ch\",source from words where source!=1 limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(OtherWord.class,sql);
    }

    @Override
    public List<OtherWord> getLianlianWordList(int curPage, int pageSize) {
        String sql = "select wid,word_group as \"word_en\",C_meaning as \"word_ch\",source from words where source=1 limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(OtherWord.class,sql);
    }


    @Override
    public List<KelinsiWord> getKelinsiWordList(int curPage, int pageSize) {
        String sql = "SELECT * FROM k_words limit "+(curPage-1)*pageSize+","+pageSize;
        return queryForList(KelinsiWord.class,sql);
    }

    @Override
    public List<OtherSentence> getOtherSentenceByWid(int wid) {
        String sql = "SELECT eid,wid,kid,word_meaning,source,E_sentence AS \"sentence_en\",C_translate AS \"sentence_ch\" FROM example WHERE wid=?;";
        return queryForList(OtherSentence.class,sql,wid);
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

    @Override
    public int getTotalCount(int dict_source) {
        String sql;
        switch (dict_source){
            case 0:
                sql = "SELECT COUNT(*) FROM words where source!=1;";
                break;
            case 1:
                sql = "SELECT COUNT(*) FROM words where source=1;";
                break;
            case 2:
                sql = "SELECT COUNT(*) FROM k_words;";
                break;
            default:
                return 0;
        }
        return Integer.parseInt(queryForSingleValue(sql).toString());
    }

    @Override
    public void deleteWordByWid(int wid,int dict_source) {
        String sql;
        switch (dict_source){
            case 0: case 1: default:
                sql="delete from words where wid=?";
                break;
            case 2:
                sql="delete from k_words where wid=?";
                break;
        }
        update(sql,wid);
    }

    @Override
    public int insertWord(OtherWord otherWord) {
        String sql = "insert into words(word_group,C_meaning,source)values(?,?,?)";
        update(sql,otherWord.getWord_en(),otherWord.getWord_ch(),otherWord.getSource());
        return getInsertId();
    }

    @Override
    public int insertOtherSentence(OtherSentence otherSentence) {
        String sql = "insert into example(word_meaning,E_sentence,C_translate,wid,source)values(?,?,?,?,?);";
        update(sql,otherSentence.getWord_meaning(),otherSentence.getSentence_en(),otherSentence.getSentence_ch(),otherSentence.getWid(),otherSentence.getSource());
        return getInsertId();
    }


}
