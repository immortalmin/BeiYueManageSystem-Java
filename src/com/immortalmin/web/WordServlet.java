package com.immortalmin.web;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.word.OtherWord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordServlet extends BaseServlet{
    WordDao wordDao = new WordDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void listAllOtherWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");
        List<OtherWord> wordList = wordDao.getOtherWordList(Integer.parseInt(curPage),Integer.parseInt(pageSize));
        request.setAttribute("wordList",wordList);
        request.getRequestDispatcher("pages/word/OtherWord.jsp").forward(request,response);
    }
}
