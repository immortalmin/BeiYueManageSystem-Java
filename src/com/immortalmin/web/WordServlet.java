package com.immortalmin.web;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.word.OtherSentence;
import com.immortalmin.pojo.word.OtherWord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordServlet extends BaseServlet {
    WordDao wordDao = new WordDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //FIXME:现在只是获取“用户添加的单词”的相关信息，无法获取其他词典的单词数据。之后可以添加dict_source参数来获取不同词典的数据
    protected void listAllOtherWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");
        List<OtherWord> wordList = wordDao.getOtherWordList(Integer.parseInt(curPage), Integer.parseInt(pageSize));
        request.setAttribute("wordList", wordList);
        request.setAttribute("totalCount", wordDao.getTotalCount(0));
        request.setAttribute("curPage", curPage);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("pages/word/OtherWord.jsp").forward(request, response);
    }

    protected void listOtherWordDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wid = request.getParameter("wid");
        OtherWord otherWord = wordDao.getOtherWordByWid(Integer.parseInt(wid));
        request.setAttribute("otherWord", otherWord);
        List<OtherSentence> otherSentence = wordDao.getOtherSentenceByWid(Integer.parseInt(wid));
        request.setAttribute("otherSentences", otherSentence);
        request.getRequestDispatcher("pages/word/WordDetail.jsp").forward(request, response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wid = request.getParameter("wid");
        wordDao.deleteWordByWid(Integer.valueOf(wid));
    }

    protected void importWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        InputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println("res:"+s);
        }
    }

}
