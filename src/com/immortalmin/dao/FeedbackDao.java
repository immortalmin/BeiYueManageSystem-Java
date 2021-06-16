package com.immortalmin.dao;

import com.immortalmin.pojo.Feedback;

import java.util.List;

public interface FeedbackDao {

    /**
     * ͨ��Fid����ѯ�û�����
     * @param fid
     * @return
     */
    Feedback queryFeedbackByFid(int fid,int what);

    /**
     * ��ѯ���е��û�����
     * @param curPage ��ǰҳ��
     * @param pageSize ÿҳ���ݵ�����
     * @return
     */
    List<Feedback> queryAllFeedback(int curPage,int pageSize);

    /**
     * ��ȡ��¼��
     */
    int queryTotalCount();

    /**
     * ɾ���û�����
     */
    void deleteFeedback(int fid,int what);

    /**
     * ���½���
     * @param fid
     * @param what 0��������   1������
     * @param progress_id 0:������ - 0:������; 1:�Ѳ���; 2:δ����; 3:ʵ����; 4:��ʵ��;
     *                    1:���    - 0:������; 1:�޸���; 2:���޸�;
     */
    void updateFeedbackProgress(int fid,int what,int progress_id);


}
