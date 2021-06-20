package com.immortalmin.service.impl;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.word.OtherSentence;
import com.immortalmin.pojo.word.OtherWord;
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
}
