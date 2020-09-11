package com.wql.user.dao;

import com.wql.user.model.ChallengeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChallengeDao {
    public int insertChallenge(ChallengeEntity entity);
    public List<ChallengeEntity> findChallengeWithUserID(@Param("user_id")Integer userID,
                                                         @Param("from_index")Integer from,
                                                         @Param("count")Integer count);

}

