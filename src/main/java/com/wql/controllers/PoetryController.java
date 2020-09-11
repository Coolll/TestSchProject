package com.wql.controllers;

import com.wql.baseFile.BaseParam;
import com.wql.poetry.model.ImageEntity;
import com.wql.poetry.model.PoetryEntity;
import com.wql.poetry.model.SearchPoetryParam;
import com.wql.poetry.param.*;
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

    //更新诗词（存在的情况下）
    @RequestMapping(value = "api/poetry/update",method = RequestMethod.POST)
    public Object updatePoetry(@RequestBody PoetryEntity param){
        return service.updatePoetry(param);
    }

    //存在更新，不存在新增 诗词
    @RequestMapping(value = "api/poetry/updateOrInsert",method = RequestMethod.POST)
    public Object updateOrInsertPoetry(@RequestBody PoetryEntity param){
        return service.updateOrInsertPoetry(param);
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

    //获取首页的题画背景图
    @RequestMapping(value = "api/poetry/loadHomeTopImage",method = RequestMethod.POST)
    public Object loadHomeTopImage(@RequestBody BaseParam param){
        return service.loadHomeTopImage(param);
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

    //获取全部的头像图片
    @RequestMapping(value = "api/poetry/loadAllHeadImages",method = RequestMethod.POST)
    public Object loadAllHeadImages(@RequestBody AllHeadImageParam param){
        return service.loadAllHeadImages(param);
    }

    //获取全部的诗词配置
    @RequestMapping(value = "api/poetry/loadPoetryConfigure",method = RequestMethod.POST)
    public Object loadAllPoetryConfigure(@RequestBody BaseParam param){
        return service.loadAllPoetryConfigure(param);
    }

    //根据mainClass获取对应的诗词（不含翻译）
    @RequestMapping(value = "api/poetry/loadPoetryWithMainClass",method = RequestMethod.POST)
    public Object loadPoetryWithMainClass(@RequestBody MainClassParam param){
        return service.loadPoetryWithParam(param);
    }


    //根据关键词获取对应的诗词
    @RequestMapping(value = "api/poetry/loadPoetryWithKeyword",method = RequestMethod.POST)
    public Object loadPoetryWithKeyword(@RequestBody SearchPoetryParam param){
        return service.loadPoetryWithKeyword(param);
    }

    //根据ID获取诗词的鉴赏信息
    @RequestMapping(value = "api/poetry/loadPoetryAnalysesInfo",method = RequestMethod.POST)
    public Object loadPoetryAnalysesInfo(@RequestBody LoadPoetryAnalysesParam param){
        return service.loadPoetryAnalysesInfo(param);
    }


    //获取评测的诗词
    @RequestMapping(value = "api/poetry/loadEvaluatePoetry",method = RequestMethod.POST)
    public Object loadEvaluatePoetry(@RequestBody BaseParam param){
        return service.loadEvaluatePoetry(param);
    }
}

