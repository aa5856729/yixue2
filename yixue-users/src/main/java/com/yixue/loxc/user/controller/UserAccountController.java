package com.yixue.loxc.user.controller;

import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.entity.TUserAccountEntity;
import com.yixue.loxc.pojo.vo.UserVo;
import com.yixue.loxc.user.service.TLoginLogService;
import com.yixue.loxc.user.service.UserInfoService;
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
//@RequestMapping("/user")
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
    public Result<TUserAccountEntity> login(@RequestBody UserVo userVo,
                                            HttpSession session, HttpServletRequest request) {

        Result<TUserAccountEntity> result = null;
        result = userAccountService.login(userVo.getUsername(), userVo.getPassword());
        Integer start = 1;
        if (result == null) {
            start = 0;
            //未成功
        }
        //对登录日志进行添加 是否登录成功 ，是前台人员还是运营人员

        int s = tLoginLogService.addlog(userVo.getUsername(), start, userVo.getIp(), userVo.getAccountType());
        if (s > 0) {
            System.out.println("日志添加成功！");
        } else {
            System.out.println("日志添加失败！");
        }


        //对账号进行判断,是否是管理员用户
        if (EmptyUtils.isNotEmpty(result.getData())) {
            TUserAccountEntity accountEntity = result.getData();
            session.setAttribute("user", accountEntity);
            if (userVo.getAccountType() == 2) {
                //管理员用户
            } else {
                //普通用户

            }
            return new Result(200, "登录成功！", accountEntity);

        } else {
            return new Result(222, "登录失败，账号或密码不正确！");
        }
    }


    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/register")
    public Result<TUserAccountEntity> register(@RequestBody UserVo userVo) {
        int i = userAccountService.saveUser(userVo.getUsername(), userVo.getPassword());

        if (i > 0) {
            return new Result(200, "注册成功", null);
        }
        return new Result(400, "注册失败", null);
    }


    /**
     * 判断用户是否已存在
     */
    @PostMapping("/checkUsername")
    public Boolean checkUsername(@RequestParam("username") String username) {
        Result<TUserAccountEntity> result = null;
        result = userAccountService.isuser(username);
        if (result.getData() == null) {
            return true;
        } else {
            return false;
        }
    }

}
