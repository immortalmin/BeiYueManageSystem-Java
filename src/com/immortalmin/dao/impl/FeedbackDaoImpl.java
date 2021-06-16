package com.immortalmin.dao.impl;

import com.immortalmin.dao.FeedbackDao;
import com.immortalmin.pojo.Feedback;

import java.util.List;

public class FeedbackDaoImpl extends BaseDao implements FeedbackDao {

    @Override
    public Feedback queryFeedbackByFid(int fid,int what) {
        String sql;
        if(what==0){
            sql = "select * from error_feedback where eid=?";
        }else{
            sql = "select * from feature_suggestions where fid=?";
        }
        return queryForOne(Feedback.class,sql,fid);
    }

    @Override
    public List<Feedback> queryAllFeedback(int curPage,int pageSize) {
        String sql = "SELECT eid AS \"fid\",user.`uid`,user.`login_mode`,username,profile_photo,phone_model,description,contact,add_time,progress,img_path,\"0\" as \"what\" FROM error_feedback INNER JOIN user where error_feedback.`uid`=user.`uid` UNION SELECT fid,user.`uid`,user.`login_mode`,username,profile_photo,\"null\" AS \"phone_model\",description,contact,add_time,progress,img_path,\"1\" as \"what\" FROM feature_suggestions INNER JOIN user where feature_suggestions.`uid`=user.`uid` order by add_time desc limit "+(curPage-1)*pageSize+","+pageSize+";";
        return queryForList(Feedback.class,sql);
    }

    @Override
    public int queryTotalCount() {
        String sql = "select sum(a.x) from (select count(*) as x from error_feedback union all select count(*) as x from feature_suggestions) as a;";
        return Integer.parseInt(queryForSingleValue(sql).toString());
    }

    @Override
    public void deleteFeedback(int fid,int what) {
        String sql;
        if(what==0){
            sql = "delete from error_feedback where eid = ?";
        }else{
            sql = "delete from feature_suggestions where fid = ?";
        }
        update(sql,fid);
    }

    @Override
    public void updateFeedbackProgress(int fid, int what, int progress_id) {
        String sql;
        String[] progressStr1 = {"待处理","已采纳","未采纳","实现中","已实现"};
        String[] progressStr2 = {"待处理","修复中","已修复"};
        if(what==0){
            sql = "update error_feedback set progress=? where eid=?";
            update(sql,progressStr2[progress_id],fid);
        }else{
            sql = "update feature_suggestions set progress=? where fid=?";
            update(sql,progressStr1[progress_id],fid);
        }
    }
}
