package com.immortalmin.test;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.word.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WordDaoTest {
    WordDao wordDao = new WordDaoImpl();

    @Test
    public void getOtherWordList() {
        List<OtherWord> res = wordDao.getOtherWordList(1,10);
        for(OtherWord word:res){
            System.out.println(word.getWid()+" "+word.getWord_en()+" "+word.getWord_ch());
        }
    }

    @Test
    public void getKelinsiWordList(){
        List<KelinsiWord>res = wordDao.getKelinsiWordList(1,10);
        for(KelinsiWord word:res){
            System.out.println(word.getWid()+" "+word.getWord_en()+" "+word.getWord_ch()+" "+word.getStar());
        }
    }

    @Test
    public void getOtherSentenceByWid() {
        List<OtherSentence> otherSentence = wordDao.getOtherSentenceByWid(10);
        System.out.println(otherSentence.toString());
    }

    @Test
    public void getKelinsiSentenceByIid(){
        List<KelinsiSentence> res = wordDao.getKelinsiSentencesByIid(100);
        System.out.println(res);
    }

    @Test
    public void getKelinsiItemsByWid(){
        List<KelinsiItem> res = wordDao.getKelinsiItemsByWid(100);
        System.out.println(res);
    }

    @Test
    public void getTotalCount(){
        int res = wordDao.getTotalCount(1);
        System.out.println(res);
    }

    @Test
    public void getOtherWordByWid(){
        OtherWord otherWord = wordDao.getOtherWordByWid(100);
        System.out.println(otherWord.toString());
    }

    @Test
    public void getKelinsiWordByWid(){
        KelinsiWord kelinsiWord = wordDao.getKelinsiWordByWid(100);
        System.out.println(kelinsiWord.toString());
    }
}