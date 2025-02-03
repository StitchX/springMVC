package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/2 17:45
 */

@Controller
@RequestMapping("/hello")
public class RequestMappingController {

//    @RequestMapping("/testRequestMapping")
//    @RequestMapping(
//            value = {"/testRequestMapping","/test"}
//    )
    @RequestMapping(
            value = {"/testRequestMapping","/test"},
    //            不写下面的method，则
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String success(){
        return "success";
    }

    @GetMapping("/testRequestMapping")
    public String testGetMapping(){
        return "success";
    }

    @RequestMapping(
            value = "/testParamsAndHeaders",
            params = {"username","password=12345"}, // params = {"!username","password!=12345"}
            headers = {"Host=localhost:8080"}
    )
    public String testParamsAndHeaders(){
        return "success";
    }

//    @RequestMapping("/a?a/testAnt") // "/a**a/testAnt" 这样写是错的
    @RequestMapping("/a*a/testAnt") // "/a**a/testAnt" 这样写是错的
//    @RequestMapping("/**/testAnt") //报500了，不知道啥错。 "/a**a/testAnt" 这样写是错的
    public String testAnt(){
        return "success";
    }

    @RequestMapping("/testPath/{id}/{username}") // "/a**a/testAnt" 这样写是错的
    public String testPath(@PathVariable("id")Integer id,@PathVariable("username")String username){
        System.out.println("id:"+id);
        System.out.println("username:"+username);
        return "success";
    }

}
