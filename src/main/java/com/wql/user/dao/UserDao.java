package com.wql.user.dao;

import com.wql.poetry.model.CollectionEntity;
import com.wql.user.model.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    public UserEntity findUserInfo(@Param("nick_name")String nick_name,
                                   @Param("user_id")Integer user_id);

    public int insertUser(UserEntity entity);

    public void updateUser(UserEntity entity);

    public void updateUserHeadImage(@Param("head_image")String head_image,
                                    @Param("user_id")Integer user_id);

    //查找一个人的收藏的诗词(仅返回ID)
    public List<Integer> findUserLikeList(@Param("user_id")Integer userId);

    //查找一个人的收藏的诗词(返回entity)
    public List<CollectionEntity> findUserCollectionList(@Param("user_id")Integer userId,
                                                         @Param("from_index")Integer from,
                                                         @Param("count")Integer count);


}
