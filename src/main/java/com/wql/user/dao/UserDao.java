package com.wql.user.dao;

import com.wql.user.model.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    public UserEntity findUserInfo(@Param("nick_name")String nick_name,
                                   @Param("user_id")Integer user_id);

    public int insertUser(UserEntity entity);

    public void updateUser(UserEntity entity);



}
