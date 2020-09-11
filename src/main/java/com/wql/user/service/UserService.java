package com.wql.user.service;

import com.wql.baseFile.BaseService;
import com.wql.poetry.dao.PoetryDao;
import com.wql.poetry.model.CollectionEntity;
import com.wql.user.dao.ChallengeDao;
import com.wql.user.model.ChallengeEntity;
import com.wql.user.param.*;
import com.wql.user.dao.UserDao;
import com.wql.user.model.UserEntity;
import com.wql.utils.publicUtils.PublicUtil;
import com.wql.utils.publicUtils.myBatisUtil;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import com.wql.utils.security.AESHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.Base64;
import java.util.List;

public class UserService extends BaseService {

    //注册与登录
    public Object loginAndRegister(LoginParam param){
        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        LoginParam decryptedParam = this.decryptedAESDataToEntity(param,LoginParam.class);

        String nickName = decryptedParam.getNickName();
        String password = decryptedParam.getPassword();

        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        UserDao dao = session.getMapper(UserDao.class);

        try {
            UserEntity entity = dao.findUserInfo(nickName,null);
            if (entity != null){
                //用户已存在
                String databasePwd = entity.getPassword();
                if (databasePwd.equals(password)){
                    //密码匹配
                    entity.setLast_login_time(PublicUtil.loadBeijingTime());
                    String tokenString = this.createToken(nickName,password,PublicUtil.loadBeijingTime());
                    entity.setUser_token(tokenString);
                    dao.updateUser(entity);
                }else {
                    //密码不匹配
                    return Result.error(CodeMsg.MESSAGE_TIP,"账号已存在，密码不正确");
                }

            }else {
                //用户不存在，则需要新增一个用户了
                entity = new UserEntity();
                entity.setCreate_time(PublicUtil.loadBeijingTime());
                entity.setLast_login_time(PublicUtil.loadBeijingTime());
                entity.setNick_name(nickName);
                entity.setPassword(password);
                String tokenString = this.createToken(nickName,password,PublicUtil.loadBeijingTime());
                entity.setUser_token(tokenString);
                dao.insertUser(entity);
            }
            session.commit();
            return Result.success(entity);

        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"登录失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }
    //创建token 登录时调用
    public String createToken(String userName,String pwd,String loginTime){

        try {
            String newString = userName+"&"+pwd+"&"+loginTime;
            String enString = AESHelper.encryptString(newString);
            return enString;
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            return "";
        }

    }


    //获取用户收藏的诗词(仅ID)
    public Object loadUserLikePoetry(LikePoetryParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        LikePoetryParam decryptedParam = this.decryptedAESDataToEntity(param,LikePoetryParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        UserDao dao = session.getMapper(UserDao.class);

        try {
            List<Integer> list = dao.findUserLikeList(decryptedParam.getUser_id());

            //更新用户的操作
            UserDao userDao = session.getMapper(UserDao.class);
            UserEntity userEntity = userDao.findUserInfo(null,decryptedParam.getUser_id());
            if (userEntity != null){
                userEntity.setLast_update_time(PublicUtil.loadBeijingTime());
                userDao.updateUser(userEntity);
            }
            session.commit();
            return Result.success(list);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取收藏列表失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }

    //获取用户收藏的诗词包含内容
    public Object loadUserLikePoetryEntity(PoetryCollectionParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        PoetryCollectionParam decryptedParam = this.decryptedAESDataToEntity(param,PoetryCollectionParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        UserDao dao = session.getMapper(UserDao.class);

        try {
            List<CollectionEntity> list = dao.findUserCollectionList(decryptedParam.getUser_id(),decryptedParam.getFrom_index(),decryptedParam.getCount());
            session.commit();
            return Result.success(list);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取收藏列表失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }

    //退出登录
    public Object logoutAction(LogoutParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        LogoutParam decryptedParam = this.decryptedAESDataToEntity(param,LogoutParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        UserDao dao = session.getMapper(UserDao.class);

        try {
            UserEntity entity = dao.findUserInfo(null,decryptedParam.getUser_id());
            if (entity != null){
                //用户存在，则把token清理掉，更新一下退出时间
                entity.setLast_logout_time(PublicUtil.loadBeijingTime());
                entity.setUser_token("");
                dao.updateUser(entity);

            }else {
                //用户不存在
                return Result.error(CodeMsg.MESSAGE_TIP,"用户不存在");
            }
            session.commit();
            return Result.success(entity);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"退出失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }


    //添加一条挑战记录
    public Object insertChallengeRecord(AddChallengeParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        AddChallengeParam decryptedParam = this.decryptedAESDataToEntity(param,AddChallengeParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        ChallengeDao dao = session.getMapper(ChallengeDao.class);

        try {
            ChallengeEntity entity = new ChallengeEntity();
            Integer storage = decryptedParam.getStorage();
            Integer poetryClass = 0;
            if (storage < 200){ poetryClass = 0; }
            else if (storage < 700){ poetryClass = 1;}
            else if (storage < 1300){ poetryClass = 2;}
            else if (storage < 2000){ poetryClass = 3;}
            else if (storage < 2800){ poetryClass = 4;}
            else if (storage < 3700){ poetryClass = 5;}
            else if (storage < 4500){ poetryClass = 6;}
            else { poetryClass = 7;}

            entity.setUser_id(decryptedParam.getUser_id());
            entity.setPoetry_class(poetryClass);
            entity.setStorage(decryptedParam.getStorage());
            entity.setChallenge_time(PublicUtil.loadBeijingTime());

            dao.insertChallenge(entity);

            UserDao userDao = session.getMapper(UserDao.class);
            UserEntity userEntity = userDao.findUserInfo(null,decryptedParam.getUser_id());
            if (userEntity != null){
                userEntity.setPoetry_storage(decryptedParam.getStorage().toString());
                userEntity.setUser_poetry_class(poetryClass.toString());
                userEntity.setLast_update_time(PublicUtil.loadBeijingTime());
                userDao.updateUser(userEntity);
            }
            session.commit();
            return Result.success(entity);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"创建挑战记录失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }


    //获取用户的近期挑战记录（10条）
    public Object loadUserChallenge(UserChallengeParam param){

        boolean verifySuccess = this.checkIdentity(param);
        if (!verifySuccess){
            logger.error("身份校验失败");
            return Result.error(CodeMsg.VERIFY_FAILED);
        }

        UserChallengeParam decryptedParam = this.decryptedAESDataToEntity(param,UserChallengeParam.class);
        SqlSession session = myBatisUtil.openPoetrySqlFactory().openSession();
        ChallengeDao dao = session.getMapper(ChallengeDao.class);

        try {
            List<ChallengeEntity> list = dao.findChallengeWithUserID(decryptedParam.getUser_id(),0,10);
            session.commit();
            return Result.success(list);
        }catch (Exception e){
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            dealException(e,element.getFileName(),element.getLineNumber());
            session.rollback();
            return Result.error(CodeMsg.SERVER_EXCEPTION,"获取挑战记录失败");
        }finally {
            if (session != null){
                session.close();
            }
        }

    }


}
