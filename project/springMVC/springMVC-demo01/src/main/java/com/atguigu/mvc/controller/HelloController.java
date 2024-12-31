package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2024/12/30 22:45
 */

@Controller
public class HelloController {
//    "/"-->/WEB-INF/template/index.html
    @RequestMapping(value = "/")
    public String index(){
//        返回视图名称
        return "index";
    }
    @RequestMapping(value = "/target")
    public String toTarget(){
        return "target";
    }
}
