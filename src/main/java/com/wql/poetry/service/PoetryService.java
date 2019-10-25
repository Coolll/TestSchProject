package com.wql.poetry.service;

import com.wql.baseFile.BaseService;
import com.wql.poetry.dao.ImageDao;
import com.wql.poetry.dao.PoetryDao;
import com.wql.poetry.model.ImageEntity;
import com.wql.poetry.model.PoetryEntity;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import org.apache.ibatis.session.SqlSession;
import com.wql.utils.publicUtils.myBatisUtil;

public class PoetryService extends BaseService {


    public Object addPoetry(PoetryEntity entity){

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            dao.insertPoetry(entity);
            session.commit();
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"插入诗词失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

        return "";
    }

    public Object addImage(ImageEntity entity){

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        ImageDao dao = session.getMapper(ImageDao.class);

        try {
            dao.insertImage(entity);
            session.commit();
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"插入背景图片失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

        return "";
    }

}
