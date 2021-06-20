package com.immortalmin.service.impl;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.word.*;
import com.immortalmin.service.WordService;
import com.immortalmin.utils.StringUtils;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordServiceImpl implements WordService {

    private WordDao wordDao = new WordDaoImpl();

    @Override
    public List<OtherWord> analysisWordString(ServletContext context, int uid){
        List<OtherWord> wordList = new ArrayList<>();
        String path = context.getRealPath("/files");
        File file = new File(path+"/importFile.txt");   // 创建 file对象
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String word_en,word_ch,word_meaning,sentence_en,sentence_ch;
            boolean flag=false;
            int last_insert_id;
            word_en = reader.readLine();
            while((word_ch = reader.readLine())!=null){
                OtherWord otherWord = new OtherWord();
                otherWord.setWord_en(word_en);
                otherWord.setWord_ch(word_ch);
                otherWord.setSource(uid);
                if((word_en = reader.readLine())==null) break;
                List<OtherSentence> sentenceList = new ArrayList<>();
                while(StringUtils.isIncludeChinese(word_en)){
                    word_meaning = word_en;
                    sentence_en = reader.readLine();
                    sentence_ch = reader.readLine();
                    OtherSentence otherSentence = new OtherSentence();
                    otherSentence.setWord_meaning(word_meaning);
                    otherSentence.setSentence_en(sentence_en);
                    otherSentence.setSentence_ch(sentence_ch);
                    otherSentence.setSource(uid);
                    sentenceList.add(otherSentence);
                    if((word_en = reader.readLine())==null){
                        flag = true;
                        break;
                    }
                }
                otherWord.setSentences(sentenceList);
                wordList.add(otherWord);
                if(flag) break;
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println(wordList.toString());
        return wordList;
    }

    @Override
    public void importWord(List<OtherWord> wordList){
        WordDao wordDao = new WordDaoImpl();
        for(OtherWord otherWord:wordList){
            int wid = wordDao.insertWord(otherWord);
            for(OtherSentence otherSentence:otherWord.getSentences()){
                otherSentence.setWid(wid);
                wordDao.insertOtherSentence(otherSentence);
            }
        }

    }

    @Override
    public <T> List<T> getWordList(Class<T> type,int curPage, int pageSize, int dict_source) {
        List<T> res = new ArrayList<>();
        switch (dict_source){
            case 0://用户添加的单词
                List<OtherWord> wordList = wordDao.getOtherWordList(curPage, pageSize);
                for(OtherWord word:wordList) res.add((T) word);
                break;
            case 1://恋练有词
                wordList = wordDao.getLianlianWordList(curPage, pageSize);
                for(OtherWord word:wordList) res.add((T) word);
                break;
            case 2://柯林斯词典
                List<KelinsiWord> wordList2 = wordDao.getKelinsiWordList(curPage, pageSize);
                for(KelinsiWord word:wordList2) res.add((T) word);
                break;
        }
        return res;
    }

    @Override
    public <T> T getWordDetail(Class<T> type, int wid, int dict_source) {
        switch (dict_source){
            case 0:case 1://用户添加的单词、恋练有词
                return (T)wordDao.getOtherWordByWid(wid);
            case 2://柯林斯词典
                return (T)wordDao.getKelinsiWordByWid(wid);
        }
        return null;
    }

    @Override
    public <T> List<T> getSentences(Class<T> type, int wid, int dict_source) {
        List<T> res = new ArrayList<>();
        switch (dict_source){
            case 0: case 1:
                List<OtherSentence> list = wordDao.getOtherSentenceByWid(wid);
                for(OtherSentence sentence:list) res.add((T)sentence);
                break;
            case 2:
                List<KelinsiItem> list2 = wordDao.getKelinsiItemsByWid(wid);
                for(KelinsiItem item:list2) res.add((T)item);
                break;
        }
        return res;
    }


}
