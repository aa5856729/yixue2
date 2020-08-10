package com.yixue.loxc.user.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.entity.TUserAccountEntity;
import com.yixue.loxc.user.service.TLoginLogService;
import com.yixue.loxc.user.service.UserInfoService;
import com.yixue.loxc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.yixue.loxc.user.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-05 16:11:11
 */
@RestController
@CrossOrigin
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TLoginLogService tLoginLogService;

    /**
     * 登录
     */
    @PostMapping(value = "/login", produces = "application/json")
    public Result<TUserAccountEntity> login(@RequestBody UserVO userVO, HttpSession session) {

        System.out.println("username:" + userVO.getUsername() + ",passwoed:" + userVO.getPassword());

        Result<TUserAccountEntity> result = null;
        result = userAccountService.login(userVO.getUsername(), userVO.getPassword());
        Integer  start=1;
        if (result==null){
            start=0;
            //未成功
        }
        //对登录日志进行添加 是否登录成功 ，是前台人员还是运营人员

        int s= tLoginLogService.addlog(userVO.getUsername(),start,userVO.getIp());
        if (s>0){
            System.out.println("日志添加成功！");
        }else {
            System.out.println("日志添加失败！");
        }


        if (result.getData() != null) {
            TUserAccountEntity accountEntity = result.getData();
            session.setAttribute("user", accountEntity);
            return new Result(200, "登录成功！", accountEntity);

        } else {
            return new Result(222, "登录失败，账号或密码不正确！", null);
        }
    }


    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public Result<TUserAccountEntity> register(@RequestBody UserVO userVO){
        int i = userAccountService.saveUser(userVO.getUsername(), userVO.getPassword());

         if (i>0){
             return new Result(200,"注册成功", null);
         }
             return new Result(400, "注册失败", null);
    }


    /**
     * 判断用户是否已存在
     */
    @PostMapping("/checkUsername")
    public Boolean checkUsername(String username) {
        Result<TUserAccountEntity> result = null;
        result = userAccountService.isuser(username);
        if (result.getData()==null) {
            return true;
        }else {
            return false;
        }
    }

}
