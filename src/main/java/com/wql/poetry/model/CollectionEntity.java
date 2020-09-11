package com.wql.poetry.model;

public class CollectionEntity {
    private Integer user_id;
    private Integer poetry_id;
    private PoetryEntity poetryEntity;
    private ImageEntity imageEntity;

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
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

    public PoetryEntity getPoetryEntity() {
        return poetryEntity;
    }

    public void setPoetryEntity(PoetryEntity poetryEntity) {
        this.poetryEntity = poetryEntity;
    }
}
