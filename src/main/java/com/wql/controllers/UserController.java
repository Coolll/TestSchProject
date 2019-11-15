package com.wql.controllers;

import com.wql.poetry.model.PoetryEntity;
import com.wql.user.param.LikePoetryParam;
import com.wql.user.param.LoginParam;
import com.wql.user.model.UserEntity;
import com.wql.user.param.LogoutParam;
import com.wql.user.param.PoetryCollectionParam;
import com.wql.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService service = new UserService();

    //登录与注册
    @RequestMapping(value = "api/user/loginAndRegister",method = RequestMethod.POST)
    public Object loginAndRegister(@RequestBody LoginParam param){

        return service.loginAndRegister(param);
    }
    //退出登录
    @RequestMapping(value = "api/user/logout",method = RequestMethod.POST)
    public Object loginOut(@RequestBody LogoutParam param){

        return service.logoutAction(param);
    }

    //获取全部的收藏（仅id）
    @RequestMapping(value = "api/user/allLikesList",method = RequestMethod.POST)
    public Object allLikesList(@RequestBody LikePoetryParam param){

        return service.loadUserLikePoetry(param);
    }

    //获取全部的收藏（包含诗词）
    @RequestMapping(value = "api/user/loadCollectionList",method = RequestMethod.POST)
    public Object allCollectionsList(@RequestBody PoetryCollectionParam param){

        return service.loadUserLikePoetryEntity(param);
    }
}
