package com.wql.controllers;

import com.wql.poetry.model.ImageEntity;
import com.wql.poetry.model.PoetryEntity;
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

    //新增诗词
    @RequestMapping(value = "api/image/insert",method = RequestMethod.POST)
    public Object insertImage(@RequestBody ImageEntity param){
        return service.addImage(param);
    }
}

