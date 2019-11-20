package com.wql.controllers;

import com.wql.poetry.model.PoetryEntity;
import com.wql.user.param.*;
import com.wql.user.model.UserEntity;
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

    //新增一条挑战记录
    @RequestMapping(value = "api/user/insertChallengeRecord",method = RequestMethod.POST)
    public Object insertChallengeRecord(@RequestBody AddChallengeParam param){

        return service.insertChallengeRecord(param);
    }

    //获取某个人的近期挑战（默认最近的10次）
    @RequestMapping(value = "api/user/loadUserChallenges",method = RequestMethod.POST)
    public Object loadUserChallenges(@RequestBody UserChallengeParam param){

        return service.loadUserChallenge(param);
    }


}
