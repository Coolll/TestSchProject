package com.wql.user.model;

public class ChallengeEntity {
    private Integer challenge_id;
    private Integer user_id;
    private Integer storage;
    private Integer poetry_class;
    private String challenge_time;

    public Integer getChallenge_id() {
        return challenge_id;
    }

    public void setChallenge_id(Integer challenge_id) {
        this.challenge_id = challenge_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getPoetry_class() {
        return poetry_class;
    }

    public void setPoetry_class(Integer poetry_class) {
        this.poetry_class = poetry_class;
    }

    public String getChallenge_time() {
        return challenge_time;
    }

    public void setChallenge_time(String challenge_time) {
        this.challenge_time = challenge_time;
    }
}
