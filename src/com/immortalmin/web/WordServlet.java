package com.immortalmin.web;

import com.immortalmin.dao.WordDao;
import com.immortalmin.dao.impl.WordDaoImpl;
import com.immortalmin.pojo.User;
import com.immortalmin.pojo.word.KelinsiItem;
import com.immortalmin.pojo.word.KelinsiWord;
import com.immortalmin.pojo.word.OtherSentence;
import com.immortalmin.pojo.word.OtherWord;
import com.immortalmin.service.WordService;
import com.immortalmin.service.impl.WordServiceImpl;
import com.immortalmin.utils.ImportWordUtils;
import com.immortalmin.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WordServlet extends BaseServlet {
    private WordDao wordDao = new WordDaoImpl();
    private WordService wordService = new WordServiceImpl();
    private List<OtherWord> wordList = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //FIXME:现在只是获取“用户添加的单词”的相关信息，无法获取其他词典的单词数据。之后可以添加dict_source参数来获取不同词典的数据
//    protected void listAllOtherWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String curPage = request.getParameter("curPage");
//        String pageSize = request.getParameter("pageSize");
//        List<OtherWord> wordList = wordDao.getOtherWordList(Integer.parseInt(curPage), Integer.parseInt(pageSize));
//        request.setAttribute("wordList", wordList);
//        request.setAttribute("totalCount", wordDao.getTotalCount(0));
//        request.setAttribute("curPage", curPage);
//        request.setAttribute("pageSize", pageSize);
//        request.getRequestDispatcher("pages/word/OtherWord.jsp").forward(request, response);
//    }

    protected void listAllWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int dict_source = Integer.parseInt(request.getParameter("dict_source"));
        switch (dict_source){
            case 0: case 1:
                request.setAttribute("wordList",wordService.getWordList(OtherWord.class,curPage,pageSize,dict_source));
                request.setAttribute("totalCount", wordDao.getTotalCount(dict_source));
                request.setAttribute("curPage", curPage);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("dict_source", dict_source);
                request.getRequestDispatcher("pages/word/OtherWord.jsp").forward(request, response);
                break;
            case 2:
                request.setAttribute("wordList",wordService.getWordList(KelinsiWord.class,curPage,pageSize,dict_source));
                request.setAttribute("totalCount", wordDao.getTotalCount(dict_source));
                request.setAttribute("curPage", curPage);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("dict_source", dict_source);
                request.getRequestDispatcher("pages/word/KelinsiWord.jsp").forward(request, response);
                break;
        }

    }

//    protected void listOtherWordDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String wid = request.getParameter("wid");
//        OtherWord otherWord = wordDao.getOtherWordByWid(Integer.parseInt(wid));
//        request.setAttribute("otherWord", otherWord);
//        List<OtherSentence> otherSentence = wordDao.getOtherSentenceByWid(Integer.parseInt(wid));
//        request.setAttribute("otherSentences", otherSentence);
//        request.getRequestDispatcher("pages/word/OtherWordDetail.jsp").forward(request, response);
//    }

    protected void listWordDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("wid"));
        int dict_source =  Integer.parseInt(request.getParameter("dict_source"));
        request.setAttribute("dict_source",dict_source);
        switch (dict_source){
            case 0: case 1:
                request.setAttribute("word", wordService.getWordDetail(OtherWord.class,wid,dict_source));
                request.setAttribute("sentences", wordService.getSentences(OtherSentence.class,wid,dict_source));
                request.getRequestDispatcher("pages/word/OtherWordDetail.jsp").forward(request, response);
                break;
            case 2:
                request.setAttribute("word", wordService.getWordDetail(KelinsiWord.class,wid,dict_source));
                request.setAttribute("items",wordService.getSentences(KelinsiItem.class,wid,dict_source));
                request.getRequestDispatcher("pages/word/KelinsiWordDetail.jsp").forward(request, response);
                break;
        }
//        List<OtherSentence> otherSentence = wordDao.getOtherSentenceByWid(wid);
//        request.setAttribute("otherSentences", otherSentence);
//        request.getRequestDispatcher("pages/word/OtherWordDetail.jsp").forward(request, response);
    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("wid"));
        int dict_source = Integer.parseInt(request.getParameter("dict_source"));
        wordDao.deleteWordByWid(wid,dict_source);
    }
    
    protected void importWords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        servletFileUpload.setHeaderEncoding("utf-8");
        List<FileItem> items = new ArrayList<FileItem>();
        try{
            items = servletFileUpload.parseRequest(request);
        }catch (FileUploadException e){
            e.printStackTrace();
        }
        for (FileItem item:items){
            handleFileField(item);
        }
        //开始导入单词数据
        int uid = ((User)request.getSession().getAttribute("user")).getUid();
        wordList = wordService.analysisWordString(this.getServletContext(),uid);
        request.setAttribute("wordList",wordList);
        request.getRequestDispatcher("pages/word/ImportWordPreview.jsp").forward(request,response);
    }

    protected void confirmImport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wordService.importWord(wordList);
        request.setAttribute("importResult",1);
        request.setAttribute("wordList",wordList);
        request.getRequestDispatcher("pages/word/ImportWordPreview.jsp").forward(request,response);
    }

    private void handleFormField(FileItem item) {
        String fieldName = item.getFieldName();

        // 获取 普通数据项中的 value值
        String value = "";
        try {
            value = item.getString("utf-8");  // 以 utf-8的编码格式来解析 value值
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 输出到控制台
        System.out.println("fieldName:" + fieldName + "--value:" + value);
    }

    /**
     * 处理 文件数据项
     * @param item
     */
    private void handleFileField(FileItem item) {
        // 获取 当前项目下的 /files 目录的绝对位置
        String path = this.getServletContext().getRealPath("/files");
        File file = new File(path);

        if (!file.exists()) {
            file.mkdir();
        }
        try {
            item.write(new File(file.toString(), "importFile.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
