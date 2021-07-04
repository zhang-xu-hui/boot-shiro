package com.zxh;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("/aaa")
    public Object getTest() {
        return "aaa";
    }

    @GetMapping("/login")
    public Object login() {
        return "login";
    }

    @GetMapping("/login1")
    public Object login1() {
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        try {
            //3.执行登录方法
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "用户名错误";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
        return "login";
    }

    @GetMapping("/noLogin")
    public Object noLogin() {
        return "noLogin";
    }
}
