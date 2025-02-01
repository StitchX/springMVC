package com.atguigu.mvc.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/1/29 18:36
 */

@Controller
public class ScopeController {

//    使用servletAPI向request域对象共享数据
    @RequestMapping("testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest request){
        request.setAttribute("testRequestScope","hello,servletAPI");
        return "success";
    }


    /**
     * 重要：无论用什么方式最终都会把数据包装成ModelAndView
     * ModelAndView有Model和View的功能
     * Model主要用于向请求域共享数据
     * View主要用于设置视图，实现页面跳转
     */
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mav = new ModelAndView();
    //        回顾：控制器设置视图名称，视图解析器进行解析后找到最终页面
    //        处理模型数据，即向请求域request共享数据
        mav.addObject("testRequestScope","hello,ModelAndView");
    //        设置视图名称
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","hello,model");
        System.out.println(model.getClass().getName());
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("testRequestScope","hello,map");
        System.out.println(map.getClass().getName());
        return "success";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope","hello,ModelMap");
        System.out.println(modelMap.getClass().getName());
        return "success";
    }


    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,session");
        return "success";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        session.setAttribute("testApplicationScope","hello,application");
        return "success";
    }
}
