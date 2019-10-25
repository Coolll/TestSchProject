package com.wql.controllers;

import com.wql.poetry.model.PoetryEntity;
import com.wql.poetry.param.LoginParam;
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
    public Object insertPoetry(@RequestBody LoginParam param){

        return service.loginAndRegister(param.getNickName(),param.getPassword());
    }

}
