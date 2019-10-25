package com.wql.user.service;

import com.wql.baseFile.BaseService;
import com.wql.poetry.dao.PoetryDao;
import com.wql.user.dao.UserDao;
import com.wql.user.model.UserEntity;
import com.wql.utils.publicUtils.PublicUtil;
import com.wql.utils.publicUtils.myBatisUtil;
import com.wql.utils.result.CodeMsg;
import com.wql.utils.result.Result;
import org.apache.ibatis.session.SqlSession;

public class UserService extends BaseService {

    public Object loginAndRegister(String nickName,String password){
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



}
