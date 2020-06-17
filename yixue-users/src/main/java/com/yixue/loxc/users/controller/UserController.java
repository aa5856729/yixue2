package com.yixue.loxc.users.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.pojo.TUserAccountEntity;
import com.yixue.loxc.users.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)           //实现表单跨域请求
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login",produces = "application/json")
    public Result<TUserAccountEntity> login(String username, String password){

        TUserAccountEntity tUserAccount = userService.login(username,password);

        if(tUserAccount != null){
            return new Result(200,"登录成功！",tUserAccount);
        }
        System.out.println("登录失败");
        return new Result(222,"登录失败，账号或密码不正确！");
    }

}
