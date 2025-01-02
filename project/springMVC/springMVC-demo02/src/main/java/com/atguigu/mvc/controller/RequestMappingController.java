package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/2 17:45
 */

@Controller
@RequestMapping("/hello")
public class RequestMappingController {

//    @RequestMapping("/testRequestMapping")
    @RequestMapping(
            value = {"/testRequestMapping","/test"}
    )
    public String success(){
        return "sucess";
    }
}
