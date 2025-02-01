package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/2/1 19:25
 */
@Controller
public class jspController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
