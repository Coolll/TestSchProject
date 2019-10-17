package com.wql.controllers;

import com.wql.test.TestService;
import com.wql.utils.security.AESUtil;
import com.wql.utils.security.RSAUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    TestService service = new TestService();

    @RequestMapping(value = "api/v1.0/testAPI",method = RequestMethod.GET)
    public Object query(){
        return "Hello wql2019～ Poetry poetry poetry";
    }

    @RequestMapping(value = "api/v1.0/testAPI2",method = RequestMethod.GET)
    public Object queryTwo(){
        return service.testMethod();
    }

    @RequestMapping(value = "api/v1.0/testAPI3", method = RequestMethod.GET)
    public Object queryThree(@RequestParam(value = "sign",required = true)String signStr){
        String string = "1234567890";
        boolean success = RSAUtil.verifyIdentify(string,signStr);
        System.out.println("验证成功？======="+(success?"YES":"NO"));
        return success;
    }

    @RequestMapping(value = "api/v1.0/testAPI4", method = RequestMethod.GET)
    public Object queryFour(@RequestParam(value = "data",required = true)String dataString){
        String decryptData = RSAUtil.decrypt(dataString);
        System.out.println("解密结果："+decryptData);
        return decryptData;
    }


}
