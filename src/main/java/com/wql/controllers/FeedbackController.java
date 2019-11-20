package com.wql.controllers;

import com.wql.feedback.param.FeedbackParam;
import com.wql.feedback.service.FeedbackService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FeedbackController {
    public FeedbackService service = new FeedbackService();

    //新增反馈
    @RequestMapping(value = "api/service/addFeedback",method = RequestMethod.POST)
    public Object insertFeedback(@RequestBody FeedbackParam param){
        return service.addFeedback(param);
    }

}
