package com.atguigu.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/7 15:29
 */
@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username:"+username+",password:"+pwd);
        return "success";
    }

    @RequestMapping("/testParam")
    //形参位置的request表示当前请求
    public String testParam(String username,String pwd){ // 命名保持一致
        System.out.println("username:"+username+",password:"+pwd);
        return "success";
    }
}
