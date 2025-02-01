package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/29 18:00
 */

@Controller
public class TestController {

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping("/test_view")
    public String testView(){
        return "test_view";
    }


}
