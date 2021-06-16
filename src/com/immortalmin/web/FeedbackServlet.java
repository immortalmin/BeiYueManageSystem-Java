package com.immortalmin.web;

import com.immortalmin.dao.FeedbackDao;
import com.immortalmin.dao.impl.FeedbackDaoImpl;
import com.immortalmin.pojo.Feedback;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FeedbackServlet extends BaseServlet {
    private FeedbackDao feedbackDao = new FeedbackDaoImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Feedback> feedbackList = feedbackDao.queryAllFeedback(curPage,pageSize);
        request.setAttribute("feedbackList",feedbackList);
        request.setAttribute("curPage",curPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totalCount",feedbackDao.queryTotalCount());
        request.getRequestDispatcher("/pages/feedback/feedback.jsp").forward(request,response);
    }

    protected void updateProgress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fid = request.getParameter("fid");
        String what = request.getParameter("what");
        String progress = request.getParameter("progress");
        feedbackDao.updateFeedbackProgress(Integer.parseInt(fid),Integer.parseInt(what),Integer.parseInt(progress));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fid = request.getParameter("fid");
        String what = request.getParameter("what");
        feedbackDao.deleteFeedback(Integer.parseInt(fid),Integer.parseInt(what));
    }
}
