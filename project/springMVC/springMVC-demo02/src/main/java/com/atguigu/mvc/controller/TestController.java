package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/2 16:28
 */

@Controller
public class TestController {
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/param")
    public String param(){
        return "test_param";
    }
}
