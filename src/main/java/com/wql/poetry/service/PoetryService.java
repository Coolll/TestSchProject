package com.wql.poetry.service;

import com.wql.baseFile.BaseParam;
import com.wql.baseFile.BaseService;
import com.wql.poetry.dao.ImageDao;
import com.wql.poetry.dao.PoetryDao;
import com.wql.poetry.model.*;
import com.wql.poetry.param.*;
import com.wql.utils.publicUtils.PublicUtil;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import org.apache.ibatis.session.SqlSession;
import com.wql.utils.publicUtils.myBatisUtil;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

    //更新诗词
    public Object updatePoetry(PoetryEntity entity){

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            dao.updatePoetry(entity);
            session.commit();
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"更新诗词失败");
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

    public Object loadHomeTopImage(BaseParam param){
        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        ImageDao dao = session.getMapper(ImageDao.class);

        try {
            //查询热门诗词
            List<ImageEntity> imageEntityList = dao.findHomeTopImage();
            session.commit();
            return Result.success(imageEntityList);

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
            List<PoetryDetailEntity> poetryEntityList = dao.findHotPoetry(decryptedParam.getFrom_index(),decryptedParam.getCount());

            List<PoetryBackEntity> backList = new ArrayList();
            for (PoetryDetailEntity entity:poetryEntityList){
                PoetryBackEntity backEntity = new PoetryBackEntity(entity);
                backList.add(backEntity);
            }
            session.commit();
            return Result.success(backList);

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



    //根据主分类来获取对应的诗词
    public Object loadPoetryWithParam(MainClassParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        MainClassParam decryptedParam = this.decryptedAESDataToEntity(param,MainClassParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询热门诗词
            List<PoetryDetailEntity> poetryEntityList = new ArrayList<>();
            String mainClass = decryptedParam.getMain_class();
            if (mainClass.equals("34") || mainClass.equals("35") || mainClass.equals("36")){
                poetryEntityList = dao.findLunyuWithMainClass(decryptedParam.getMain_class());
            }else {
                poetryEntityList = dao.findPoetryWithMainClass(decryptedParam.getMain_class());
            }

            List<PoetryBackEntity> backList = new ArrayList();
            for (PoetryDetailEntity entity:poetryEntityList){
                PoetryBackEntity backEntity = new PoetryBackEntity(entity);
                backList.add(backEntity);
            }

            session.commit();
            return Result.success(backList);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取诗词失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }




    //根据关键词来获取对应的诗词
    public Object loadPoetryWithKeyword(SearchPoetryParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        SearchPoetryParam decryptedParam = this.decryptedAESDataToEntity(param,SearchPoetryParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询热门诗词
            List<PoetryDetailEntity> poetryEntityList = dao.findPoetryWithKeywordAndPage(decryptedParam.getKeyword(),10*decryptedParam.getPage(),10);

            List<PoetryBackEntity> backList = new ArrayList();
            for (PoetryDetailEntity entity:poetryEntityList){
                PoetryBackEntity backEntity = new PoetryBackEntity(entity);
                backList.add(backEntity);
            }

            session.commit();
            return Result.success(backList);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"搜索诗词失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }

    public Object loadPoetryAnalysesInfo(LoadPoetryAnalysesParam param){
        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        LoadPoetryAnalysesParam decryptedParam = this.decryptedAESDataToEntity(param,LoadPoetryAnalysesParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询热门诗词
            PoetryAnalysesEntity analysesEntity = dao.loadPoetryAnalyses(decryptedParam.getPoetry_id());
            session.commit();
            return Result.success(analysesEntity);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"搜索诗词失败");
        }finally {
            if (session != null){
                session.close();
            }
        }
    }


    //获取评测的诗词
    public Object loadEvaluatePoetry(BaseParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        PoetryDao dao = session.getMapper(PoetryDao.class);

        try {
            //查询诗词
            //1000-5999 简单
            //6000-10000 一般
            //10000-无限大 困难
            Random random = new Random();
            int easyRandom = random.nextInt(34);
            int generalRandom = random.nextInt(82);
            int difficultRandom = random.nextInt(500);
            List<PoetrySimpleEntity> easyList = dao.findPoetryWithLimit(1000,easyRandom,12);
            List<PoetrySimpleEntity> generalList = dao.findPoetryWithLimit(6000,generalRandom,12);
            List<PoetrySimpleEntity> difficultList = dao.findPoetryWithLimit(10000,difficultRandom,12);

            HashMap map = new HashMap();
            map.put("easy",easyList);
            map.put("general",generalList);
            map.put("difficult",difficultList);

            session.commit();
            return Result.success(map);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"搜索诗词失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }


}
