package com.wql.feedback.service;

import com.wql.baseFile.BaseService;
import com.wql.feedback.dao.FeedbackDao;
import com.wql.feedback.model.FeedbackEntity;
import com.wql.feedback.param.FeedbackParam;
import com.wql.poetry.dao.ImageDao;
import com.wql.poetry.model.ImageEntity;
import com.wql.user.dao.ChallengeDao;
import com.wql.user.param.AddChallengeParam;
import com.wql.utils.publicUtils.PublicUtil;
import com.wql.utils.publicUtils.myBatisUtil;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import org.apache.ibatis.session.SqlSession;

public class FeedbackService extends BaseService {

    //新增反馈
    public Object addFeedback(FeedbackParam param){
        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        FeedbackParam decryptedParam = this.decryptedAESDataToEntity(param,FeedbackParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        FeedbackDao dao = session.getMapper(FeedbackDao.class);

        try {
            FeedbackEntity entity = new FeedbackEntity();
            entity.setUser_id(decryptedParam.getUser_id());
            entity.setContent(decryptedParam.getContent());
            entity.setContact(decryptedParam.getContact());
            entity.setCreate_time(PublicUtil.loadBeijingTime());
            dao.insertFeedback(entity);
            session.commit();
            return Result.success();
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"添加反馈失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }
}
