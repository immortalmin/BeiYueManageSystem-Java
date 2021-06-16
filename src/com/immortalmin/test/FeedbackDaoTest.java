package com.immortalmin.test;

import com.immortalmin.dao.FeedbackDao;
import com.immortalmin.dao.impl.FeedbackDaoImpl;
import com.immortalmin.pojo.Feedback;
import com.sun.javaws.IconUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FeedbackDaoTest {

    FeedbackDao feedbackDao = new FeedbackDaoImpl();

    @Test
    public void queryFeedbackByFid() {
        Feedback feedback = feedbackDao.queryFeedbackByFid(34,1);
        System.out.println("feedback:"+feedback.toString());
    }

    @Test
    public void queryAllFeedback() {
        List<Feedback> feedbackList;
        feedbackList = feedbackDao.queryAllFeedback(2,20);
        System.out.println("feedbackList:"+feedbackList.toString());
    }

    @Test
    public void deleteFeedback() {
        feedbackDao.deleteFeedback(34,1);
    }

    @Test
    public void updateFeedbackProgress(){
        feedbackDao.updateFeedbackProgress(14,0,2);
    }

    @Test
    public void queryTotalCount(){
        int res = feedbackDao.queryTotalCount();
        System.out.println("total count:"+res);
    }


}