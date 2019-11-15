package com.wql.controllers;

import com.wql.baseFile.BaseParam;
import com.wql.poetry.model.ImageEntity;
import com.wql.poetry.model.PoetryEntity;
import com.wql.poetry.param.AllBgImagesParam;
import com.wql.poetry.param.HotPoetryParam;
import com.wql.poetry.param.LikeOrDislikeParam;
import com.wql.poetry.service.PoetryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PoetryController {

    public PoetryService service = new PoetryService();

    //新增诗词
    @RequestMapping(value = "api/poetry/insert",method = RequestMethod.POST)
    public Object insertPoetry(@RequestBody PoetryEntity param){
        return service.addPoetry(param);
    }

    //新增背景图片信息
    @RequestMapping(value = "api/image/insert",method = RequestMethod.POST)
    public Object insertImage(@RequestBody ImageEntity param){
        return service.addImage(param);
    }


    //获取热门诗词
    @RequestMapping(value = "api/poetry/loadHotPoetry",method = RequestMethod.POST)
    public Object loadHotPoetry(@RequestBody HotPoetryParam param){
        return service.loadHotPoetry(param);
    }

    //喜欢诗词/不喜欢诗词
    @RequestMapping(value = "api/poetry/likeOrDislikePoetry",method = RequestMethod.POST)
    public Object likePoetryOrNot(@RequestBody LikeOrDislikeParam param){
        return service.likeOrDislikePoetry(param);
    }

    //获取全部的背景图片
    @RequestMapping(value = "api/poetry/loadAllImages",method = RequestMethod.POST)
    public Object loadAllImages(@RequestBody AllBgImagesParam param){
        return service.loadAllImages(param);
    }


    //获取全部的诗词配置
    @RequestMapping(value = "api/poetry/loadPoetryConfigure",method = RequestMethod.POST)
    public Object loadAllPoetryConfigure(@RequestBody BaseParam param){
        return service.loadAllPoetryConfigure(param);
    }


}

