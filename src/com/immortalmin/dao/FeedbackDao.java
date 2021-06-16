package com.immortalmin.dao;

import com.immortalmin.pojo.Feedback;

import java.util.List;

public interface FeedbackDao {

    /**
     * 通过Fid来查询用户反馈
     * @param fid
     * @return
     */
    Feedback queryFeedbackByFid(int fid,int what);

    /**
     * 查询所有的用户反馈
     * @param curPage 当前页码
     * @param pageSize 每页数据的条数
     * @return
     */
    List<Feedback> queryAllFeedback(int curPage,int pageSize);

    /**
     * 获取记录数
     */
    int queryTotalCount();

    /**
     * 删除用户反馈
     */
    void deleteFeedback(int fid,int what);

    /**
     * 更新进度
     * @param fid
     * @param what 0：错误反馈   1：建议
     * @param progress_id 0:错误反馈 - 0:待处理; 1:已采纳; 2:未采纳; 3:实现中; 4:已实现;
     *                    1:意见    - 0:待处理; 1:修复中; 2:已修复;
     */
    void updateFeedbackProgress(int fid,int what,int progress_id);


}
