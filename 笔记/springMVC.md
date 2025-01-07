# 一、SpringMVC简介

### 1、什么是MVC

MVC是一种软件架构的思想，将软件按照模型、视图、控制器来划分

M：Model，模型层，指工程中的JavaBean，作用是处理数据

JavaBean分为两类：

- 一类成为实体类Bean：专门存储业务数据的，如Student、User等
- 一类称为业务处理Bean：指Service或Dao对象，专门用于处理业务逻辑和数据访问。

V：View，视图层，指工程中的html或jsp等页面，作用是与用户进行交互，展示数据

C：Controller，控制层，指工程中的servlet，作用是接收和响应浏览器

MVC的工作流程：

用户通过视图层发送请求到服务器，在服务器中请求Controller接收，Controller调用相应的Model层处理请求，处理完毕将结果返回到Controller，Controller再根据请求处理的结果找到相应的View视图，渲染数据后最终响应给浏览器

### 2、什么是SpringMVC

SpringMVC是Spring的一个后续产品，是一个子项目

SpringMVC是Spring为表述层开发提供的一套完备的解决方案。在表述层框架历经Struct、WebWork、Struct2等诸多产品的历代更迭之后，目前业界普遍选择了SpringMVC作为JavaEE项目表述层开发的**首选方案**。

> 注：三层架构分为表述层（或表示层）、业务逻辑层、数据访问层，表述层表示前台页面和后台servlet

![image-20241224230132457](C:\Users\QMacroQA\Desktop\code\springMVC\笔记\assets\image-20241224230132457.png)

servlet：init、service、destory三个周期：

1. 请求地址找到servlet
2. service：处理请求和响应
   1. 在过滤器中，设置编码
   2. 获取请求参数
   3. 根据返回结果做不同处理
      1. 比如查询结果放到指定的域对象中
      2. 记录登录成功，放到session中
   4. 根据service处理后的结果响应浏览器

### 3、SpringMVC的特点

- **Spring家族原生产品**，与IOC容器等基础设施无缝对接
- **基于原生的Servlet**，通过了功能强大的**前端控制器DispatcherServlet**，对请求和响应进行统一处理
- 表述层各细分领域需要解决的问题**全方位覆盖**，提供**全面解决方案**
- **代码清晰简洁**，大幅度提升开发效率
- 内部组件化程度高，可插拔式组件**即插即用**，想要什么功能配置相应组件即可
- **性能卓著**，尤其适合现代大型、超大型互联网项目要求



# 二、HelloWorld

### 1、开发环境

IDE：idea 2023.2.4

构建工具：maven 3.9.9

服务器：tomcat 11.0.2 （主要是支持java21）

Spring版本：6

jdk：21

### 2、创建maven工程

1、创建父工程springMVC，修改maven版本，创建子模块

![image-20241225223616183](C:\Users\QMacroQA\Desktop\code\springMVC\笔记\assets\image-20241225223616183.png)

2、配置打包方式

![image-20241225233110683](C:\Users\QMacroQA\Desktop\code\springMVC\笔记\assets\image-20241225233110683.png)

3、导入依赖

```
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>6.1.12</version>
    </dependency>

    <!-- 日志
    https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.12</version>
        <!--            <scope>test</scope>-->
    </dependency>

    <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
     当前使用的tomcat10不能使用这个javax.servlet-->
<!--        <dependency>-->
<!--            <groupId>javax.servlet</groupId>-->
<!--            <artifactId>javax.servlet-api</artifactId>-->
<!--            <version>4.0.1</version>-->
<!--            <scope>provided</scope>-->
<!--        </dependency>-->

    <!-- https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api -->
    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>6.0.0</version>
        <scope>provided</scope>
    </dependency>


    <!-- https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf-spring6 -->
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring6</artifactId>
        <version>3.1.2.RELEASE</version>
    </dependency>

</dependencies>
```

4、添加web模块

![image-20241225234747488](C:\Users\QMacroQA\Desktop\code\springMVC\笔记\assets\image-20241225234747488.png)

![image-20241225234811394](C:\Users\QMacroQA\Desktop\code\springMVC\笔记\assets\image-20241225234811394.png)

### 3、配置web.xml

注册SpringMVC的前端控制器DispatcherServlet

#### a、**默认配置方式**

此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，默认名称为<servlet-name>-servlet.xml，例如，以下配置所对应SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml

```
<!-- 配置springMVC的前端控制器，对浏览器发送的请求进行统一处理 -->
<servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <!-- 设置springMVC的核心控制器所能处理的请求的请求路径
    所匹配的请求可以是/login或.htm1或.js或.css方式的请求路径
    但是/不能匹配.jsp请求路径的请求
    /*包括所有包括.jsp请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

#### b、扩展配置方式

可通过init-param标签设置SpringMVC配置文件的位置和名称，通过load-on-startup标签设置SpringMVC前端控制器DispatcherServlet的初始化时间

```
<!-- 配置springMVC的前端控制器，对浏览器发送的请求进行统一处理 -->
<servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 配置SpringMVC配置文件的位置和名称 -->
    <init-param>
        <!-- contextConfigLocation为固定值 -->
        <param-name>contextConfigLocation</param-name>
        <!-- 使用classpath:表示从类路径査找配置文件，例如maven工程中的src/main/resources -->
        <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <!-- 作为框架的核心组件，在启动过程中有大量的初始化操作要做而这些
    操作放在第一次请求时才执行会严重影响访问速度因此需要通过此标签将启动
    控制DispatcherServlet的初始化时间提前到服务器启动时-->
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <!-- 设置springMVC的核心控制器所能处理的请求的请求路径
    所匹配的请求可以是/login或.htm1或.js或.css方式的请求路径
    但是/不能匹配.jsp请求路径的请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

> 注：
>
> <url-pattern>标签中使用/和/*的区别:*
>
> *(所匹配的请求可以是/login或.html或.js或.css方式的请求路径，但是/不能匹配.jsp请求路径的请求*
>
> *因此就可以避免在访问isp页面时，该请求被DispatcherServet处理，从而找不到相应的页面*
>
> */*则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用/*的写法



### 4、创建请求控制器

由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具
体请求的类，即请求控制器

请求控制器中每一个处理请求的方法成为控制器方法

因为SpringMVC的控制器由一个POJO(普通的java类)担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的loC容器管理，此时SpringMVC才能够识别控制器的存在

![image-20241231134949757](./assets/image-20241231134949757.png)

```
package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
}
```

### 5、创建配置springMVC的配置文件

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">


    <!-- 扫描组件 -->
    <context:component-scan base-package="com.atguigu.mvc.controller"/>

    <!-- 配置thymeleaf视图解析器 -->
    <!-- Configure Thymeleaf template resolver -->
    <bean id="templateResolver" class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/templates/" />
        <property name="suffix" value=".html" />
        <property name="templateMode" value="HTML" />
        <property name="characterEncoding" value="UTF-8" />
        <!-- 其他可选配置，如cacheable等 -->
    </bean>
    <!-- Configure Thymeleaf template engine -->
    <bean id="templateEngine" class="org.thymeleaf.spring6.SpringTemplateEngine">
        <property name="templateResolver" ref="templateResolver" />
        <!-- 其他可选配置，如additionalDialects, additionalEngines等 -->
    </bean>
    <bean id="viewResolver" class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="templateEngine" ref="templateEngine" />
        <property name="characterEncoding" value="UTF-8" />
        <!-- 其他可选配置，如contentType, order等 -->
    </bean>

</beans>
```

没有用上的部分笔记：

![image-20241231133934714](./assets/image-20241231133934714.png)

### 6、测试HelloWorld

#### a、实现对首页的访问

在请求控制器中创建处理请求的方法

```
//@RequestMapping注解：处理请求和控制器方法之间的映射关系
//@RequestMapping注解的va1ue属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
//localhost:8080/springMvc/
@RequestMapping("/")
public String index(){
//    设置视图名称
    return "index";
}
```

```
package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 实际代码
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
}
```

![image-20241231134701242](./assets/image-20241231134701242.png)

![image-20241231134718358](./assets/image-20241231134718358.png)

![image-20241231134731178](./assets/image-20241231134731178.png)

#### b、通过超链接跳转到指定页面

```
<!DOCTYPE html>
<html lang="en" xmlns:th="www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>首页</h1>
<p>href="/target"<br>
    少一个上下文路径：/springMVC，由于可以改，每次上下文路径修改则代码就会变
    不使用下面方法，会直接访问localhost:8080<br>
    使用thymeaf语法解析属性，下面是格式，自动添加上下文路径
</p>
<a th:href="@{/target}">访问目标页面target.html</a>
</body>
</html>
```

![image-20241231135833931](./assets/image-20241231135833931.png)

![image-20241231135758077](./assets/image-20241231135758077.png)

最后：

![image-20241231135954959](./assets/image-20241231135954959.png)

### 7、总结

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行消染，最终转发到视图所对应页面

---

重新创建模块：

1、和demo01创建方式一样

![image-20241231141746627](./assets/image-20241231141746627.png)

2、引入依赖，和demo01一样

![image-20241231141812306](./assets/image-20241231141812306.png)

3、在web.xml注册前端控制器。先将当前的maven工程添加web模块

![image-20241231141939843](./assets/image-20241231141939843.png)

![image-20241231141947517](./assets/image-20241231141947517.png)

4、注册DispatcherServlet前端控制器

![image-20241231142315592](./assets/image-20241231142315592.png)

5、添加spring的配置文件

![image-20241231142416641](./assets/image-20241231142416641.png)

6、访问首页，web-inf的页面

标识为控制层组件

![image-20250102162845702](./assets/image-20250102162845702.png)

扫描组件

![image-20250102163128998](./assets/image-20250102163128998.png)

配置视图解析器

![image-20250102163156482](./assets/image-20250102163156482.png)

根据视图解析器的前后缀，创建目录和首页

![image-20250102163340639](./assets/image-20250102163340639.png)

控制器中写方法，访问首页

浏览器发送请求 - > DispatcherServlet解析地址和控制器方法中的request-mapping使用的属性值匹配 ->根据返回的名称返回视图名称 ->视图解析器解析，加入前后缀 ->找到页面，返回页面

![image-20250102164136301](./assets/image-20250102164136301.png)

配置tomcat

![image-20250102164203222](./assets/image-20250102164203222.png)

![image-20250102164310631](./assets/image-20250102164310631.png)

![image-20250102164330606](./assets/image-20250102164330606.png)



# 三、RequestMapping注解

### 1、@RequestMapping注解的功能

从注解名称上我们可以看到，@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立
映射关系。

SpringMVC 接收到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。

### 2、@RequestMapping注解的位置

```
@Controller
@RequestMapping("/hello")
public class RequestMappingController {

    @RequestMapping("/testRequestMapping")
    public String success(){
        return "sucess";
    }
}
```

环境：

![image-20250102174420890](./assets/image-20250102174420890.png)

@RequestMapping标识一个类：设置映射请求的请求路径的初始信息

![image-20250102174125681](./assets/image-20250102174125681.png)

@RequestMapping标识一个方法：设置映射请求请求路径的具体信息

![image-20250102174349230](./assets/image-20250102174349230.png)

重复会报错：

![image-20250102174103900](./assets/image-20250102174103900.png)

### 3、@RequestMapping注解value的属性

@RequestMapping注解的value属性通过请求的请求地址匹配请求映射

@RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求

@RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射

```
<a th:href="@{/hello/testRequestMapping}">测试RequestMapping注解的value属性 ->/testRequestMapping</a><br>
<a th:href="@{/hello/test}">测试RequestMapping注解的value属性 ->/test</a><br>
```

```
@RequestMapping(
        value = {"/testRequestMapping","/test"}
)
public String success(){
    return "sucess";
}
```



测试：

![image-20250102180109540](./assets/image-20250102180109540.png)

![image-20250102180137057](./assets/image-20250102180137057.png)

查看属性的方法：

![image-20250102175136657](./assets/image-20250102175136657.png)

### 4、@RequestMapping注解method的属性

@RequestMapping注解的method属性通过请求的请求方式(get或post)匹配请求映射

@RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求

若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405:Request method 'POST' not supported

```
<form th:action = "@{/test}" method="post">
    <input type="submit" value="测试RequestMapping注解的method属性 ->post">
</form>
```

```
@RequestMapping(
        value = {"/testRequestMapping","/test"},
//            不写下面的method，则
        method = {RequestMethod.GET, RequestMethod.POST}
)
public String success(){
    return "sucess";
}
```

> 注
> 1、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
>
> 处理get请求的映射-->@GetMapping
> 处理post请求的映射-->@PostMapping
> 处理put请求的映射-->@PutMapping
> 处理delete请求的映射->@DeleteMapping
>
> 2、常用的请求方式有get，post，put，delete
>
> 但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符申(put或delete)，则按照默认的请求方式get处理
>
> 若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodfilter，在restful部分会讲到



### 5、@RequestMapping注解params的属性(了解)

@RequestMapping注解的params属性通过请求的请求参数匹配请求映射

@RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
"param":要求请求映射所匹配的请求必须携带param请求参数

"!param":要求请求映射所匹配的请求必须不能携带param请求参数

"param=value":要求请求映射所匹配的请求必须携带param请求参数目param=value

"param!=value”:要求请求映射所匹配的请求必须携带param请求参数但是param!=value

```
<a th:href="@{/testParamsAndHeaders(username='admin',password=12345)}">测试RequestMapping的params属性 -->/testParamsAndHeaders</a><br>
```

```
@RequestMapping(
        value = "/testParamsAndHeaders",
        params = {"username","password=12345"} // params = {"!username","password!=12345"} 代表不能有username和...
)
public String testParamsAndHeaders(){
    return "success";
}
```

> 注:
> 若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面回报错400: Parameter conditions "username, password!=123456" not met for actual request parameters:username={admin}, password={123456}

### 6、@RequestMapping注解header的属性(了解)

> @RequestMapping注解的headers属性通过请求的请求头信息匹配请求映射
>
> @RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
> "header":要求请求映射所匹配的请求必须携带header请求头信息
>
> "!header":要求请求映射所匹配的请求必须不能携带header请求头信息
>
> "header=value":要求请求映射所匹配的请求必须携带header请求头信息且header=value
>
> "header!=value”:要求请求映射所匹配的请求必须携带header请求头信息目header!=value
>
> 若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到

![image-20250107142923602](./assets/image-20250107142923602.png)

### 7、SpringMVC支持ant风格的路径

? ：表示任意的单个字符

*：表示任意的0个或多个字符

**：表示任意的一层或多层目录

注意：在使用\*\*时，只能使用/**/xxx的方式

![image-20250107143319715](./assets/image-20250107143319715.png)

![image-20250107143425896](./assets/image-20250107143425896.png)

![image-20250107143558741](./assets/image-20250107143558741.png)

### 8、SpringMVC支持路径中的占位符(重点)

原始方式：/deleteUser?id=1

rest方式：/deleteUser/1

SpringMVC路径中的占位符常用于restful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx)表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参

```
<a th:href="@{/testRest/1/admin}">测试路径中的占位符-->/testRest</a><br>
```

```
@RequestMapping("/testPath/{id}/{username}") // "/a**a/testAnt" 这样写是错的
public String testPath(@PathVariable("id")Integer id,@PathVariable("username")String username){
    System.out.println("id:"+id);
    System.out.println("username:"+username);
    return "success";
}
```



## 四、SpringMVC获取请求参数

### 1、通过ServletAPI获取

将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求
报文的对象

```
@RequestMapping("/testServletAPI")
//形参位置的request表示当前请求
public String testServletAPI(HttpServletRequest request){
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    System.out.println("username:"+username+",password:"+pwd);
    return "success";
}
```



### 2、通过控制器方法的参数获取
