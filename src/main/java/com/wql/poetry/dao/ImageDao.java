package com.wql.poetry.dao;

import com.wql.poetry.model.HeadImageEntity;
import com.wql.poetry.model.ImageEntity;

import java.util.List;

public interface ImageDao {
    public int insertImage(ImageEntity entity);

    public List<ImageEntity> findAllImages();

    public List<ImageEntity> findHomeTopImage();

    public List<HeadImageEntity> findAllHeadImages();



}
