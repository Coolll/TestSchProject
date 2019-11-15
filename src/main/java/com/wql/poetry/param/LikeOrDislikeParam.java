package com.wql.poetry.param;

import com.wql.baseFile.BaseParam;

public class LikeOrDislikeParam extends BaseParam {
    private Integer user_id;
    private Integer like;//0为不喜欢 1为喜欢
    private Integer poetry_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getPoetry_id() {
        return poetry_id;
    }

    public void setPoetry_id(Integer poetry_id) {
        this.poetry_id = poetry_id;
    }
}
