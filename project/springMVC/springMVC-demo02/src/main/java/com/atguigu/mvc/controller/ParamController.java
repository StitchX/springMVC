package com.atguigu.mvc.controller;

import com.atguigu.mvc.bean.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

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
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username:"+username+",password:"+pwd);
        return "success";
    }

    @RequestMapping("/testParam")
    //形参位置的request表示当前请求
//    若请求参数中出现多个同名的请求参数，可以再控制器方法的形参位置设置字符串类型或字符串数组接收此参数
//    若使用字符串类型的形参，最终结果为请求参数的每一个值之间使用逗号进行拼接
    public String testParam(@RequestParam("username")String username,
                            @RequestParam(value = "password",required = false,defaultValue = "123456") String pwd,
                            @RequestParam("hobby")String[] hobby,
                            @RequestHeader("Host")String host,
                            @CookieValue("JSESSIONID") String JSESSIONID){
        System.out.println("username:"+username+",password:"+pwd+"hobby:"+ Arrays.toString(hobby));
        System.out.println("username:"+username+",password:"+pwd);
        System.out.println("host:"+host);
        System.out.println("JSESSIONID:"+ JSESSIONID);
        return "success";
    }

    @RequestMapping("/testBean")
    public String testBean(User user){
        System.out.println(user);
        return "success";
    }
}
