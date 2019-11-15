package com.wql.poetry.service;

import com.wql.baseFile.BaseParam;
import com.wql.baseFile.BaseService;
import com.wql.poetry.dao.ImageDao;
import com.wql.poetry.dao.PoetryDao;
import com.wql.poetry.model.ImageEntity;
import com.wql.poetry.model.LikePoetryEntity;
import com.wql.poetry.model.PoetryConfigureEntity;
import com.wql.poetry.model.PoetryEntity;
import com.wql.poetry.param.AllBgImagesParam;
import com.wql.poetry.param.HotPoetryParam;
import com.wql.poetry.param.LikeOrDislikeParam;
import com.wql.utils.publicUtils.PublicUtil;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import org.apache.ibatis.session.SqlSession;
import com.wql.utils.publicUtils.myBatisUtil;

import java.util.List;

public class PoetryService extends BaseService {

    //备份诗词
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

    //备份图片
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

    //获取首页的热门诗词
    public Object loadHotPoetry(HotPoetryParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        HotPoetryParam decryptedParam = this.decryptedAESDataToEntity(param,HotPoetryParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询热门诗词
            List<PoetryEntity> poetryEntityList = dao.findHotPoetry(decryptedParam.getFrom_index(),decryptedParam.getCount());
            session.commit();
            return Result.success(poetryEntityList);
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

    }


    //喜欢/不喜欢 一首诗词
    public Object likeOrDislikePoetry(LikeOrDislikeParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        LikeOrDislikeParam decryptedParam = this.decryptedAESDataToEntity(param,LikeOrDislikeParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {

            LikePoetryEntity likePoetryEntity = dao.findLikePoetry(decryptedParam.getUser_id(),decryptedParam.getPoetry_id());

            //0为不喜欢
            if (decryptedParam.getLike() == 0){
                //如果确实收藏过这首诗词，则删除
                if (likePoetryEntity != null){
                    dao.dislikePoetry(decryptedParam.getUser_id(),decryptedParam.getPoetry_id());
                }
            }else {
                //1为喜欢
                //如果没有收藏过这首诗词，则收藏之
                if (likePoetryEntity == null){
                    LikePoetryEntity entity = new LikePoetryEntity();
                    entity.setPoetry_id(decryptedParam.getPoetry_id());
                    entity.setUser_id(decryptedParam.getUser_id());
                    entity.setCreate_time(PublicUtil.loadBeijingTime());
                    dao.likePoetry(entity);
                }

            }
            session.commit();
            return Result.success();
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"操作失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }



    //获取全部的背景图片
    public Object loadAllImages(AllBgImagesParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        AllBgImagesParam decryptedParam = this.decryptedAESDataToEntity(param,AllBgImagesParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        ImageDao dao = session.getMapper(ImageDao.class);

        try {
            //查询热门诗词
            List<ImageEntity> imageList = dao.findAllImages();
            session.commit();
            return Result.success(imageList);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取图片失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }

    //获取全部的诗词配置
    public Object loadAllPoetryConfigure(BaseParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        BaseParam decryptedParam = this.decryptedAESDataToEntity(param,BaseParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询热门诗词
            List<PoetryConfigureEntity> confList = dao.findAllPoetryConfigure();
            session.commit();
            return Result.success(confList);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取配置失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }





}
