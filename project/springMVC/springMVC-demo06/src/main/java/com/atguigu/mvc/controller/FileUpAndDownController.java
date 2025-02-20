package com.atguigu.mvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author Eva
 * @Date 2025/2/3 20:40
 */
@Controller
public class FileUpAndDownController {
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session)throws IOException {
        //获职ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath=servletContext.getRealPath("/static/img/1.jepg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设照响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设霄要下载方式以及下载文件的名字
        headers.add("Content-Disposition","attachment;filename=1.jepg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        String fileName = photo.getOriginalFilename();
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
//        判断photoPath所对应的路径是否存在
        if (!file.exists()){
//            若不存在，则创建目录
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        photo.transferTo(new File(finalPath));
        return "success";
    }

}
