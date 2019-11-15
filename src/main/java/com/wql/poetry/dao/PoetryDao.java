package com.wql.poetry.dao;

import com.wql.poetry.model.LikePoetryEntity;
import com.wql.poetry.model.PoetryConfigureEntity;
import com.wql.poetry.model.PoetryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoetryDao {
    //新增诗词
    public int insertPoetry(PoetryEntity entity);

    //搜寻热门诗词
    public List<PoetryEntity> findHotPoetry(@Param("from_index")Integer from,
                                            @Param("count")Integer count);



    //根据userID poetryID查找收藏的诗词
    public LikePoetryEntity findLikePoetry(@Param("user_id")Integer user_id,
                                            @Param("poetry_id")Integer poetryID);
    //喜欢某一首诗词，添加一条记录
    public void likePoetry(LikePoetryEntity entity);

    //不喜欢某一首诗词，删除一条记录
    public void dislikePoetry(@Param("user_id")Integer user_id,
                                         @Param("poetry_id")Integer poetryID);

    //获取诗词的总配置
    public List<PoetryConfigureEntity> findAllPoetryConfigure();


}
