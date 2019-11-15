package com.wql.poetry.model;

public class LikePoetryEntity {
    private Integer like_id;
    private Integer user_id;
    private Integer poetry_id;
    private String create_time;

    public Integer getLike_id() {
        return like_id;
    }

    public void setLike_id(Integer like_id) {
        this.like_id = like_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPoetry_id() {
        return poetry_id;
    }

    public void setPoetry_id(Integer poetry_id) {
        this.poetry_id = poetry_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
