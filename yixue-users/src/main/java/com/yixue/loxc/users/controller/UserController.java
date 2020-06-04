package com.yixue.loxc.users.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)           //实现表单跨域请求
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login",produces = "application/json")
    public Result<TUserAccount> login(String username, String password, HttpServletRequest request){

        TUserAccount tUserAccount = userService.login(username,password);

        System.out.println("进入了后端username:" + username + ",password:" + password);

        if(tUserAccount != null){
            System.out.println("登录成功");
            return new Result(200,"登录成功！",tUserAccount);
        }
        System.out.println("登录失败");
        return new Result(222,"登录失败，账号或密码不正确！");
    }

}
