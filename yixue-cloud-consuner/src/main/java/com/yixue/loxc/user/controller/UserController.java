package com.yixue.loxc.user.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.entity.TUserAccountEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.vo.UserVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * @Author: Liu
 * @Description: TODO
 * @Date: 2020/08/08 14:40
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private static final String PAYMENT_URL = "http://localhost:8081";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 登录
     */
    @PostMapping(value = "/login", produces = "application/json")
    public Result<TUserAccountEntity> login(UserVO userVO, HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println("获取客户端ip: " + ip);
        userVO.setIp(ip);

        return restTemplate.postForObject(PAYMENT_URL + "/login",userVO,Result.class);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public Result<TUserAccountEntity> register(UserVO userVO){
        return restTemplate.postForObject(PAYMENT_URL + "/register",userVO,Result.class);
    }

    /**
     * 判断用户是否已存在
     */
    @PostMapping("/checkUsername")
    public Boolean checkUsername(String username) {
        return restTemplate.postForObject(PAYMENT_URL + "/checkUsername",username,Boolean.class);
    }

    @PostMapping("/accountflow/query")
    public Result<TRechargeEntity> query(@RequestBody UserVO userVO) throws ParseException {
        return restTemplate.postForObject(PAYMENT_URL + "/accountflow/query",userVO,Result.class);
    }

    /**
     * 绑定银行卡数据初始化
     * @param id
     * @return
     */
    @PostMapping("/bankcard/get/{id}")
    public Result<TWithdrawEntity> get(@PathVariable String id){
        return restTemplate.getForObject(PAYMENT_URL + "/bankcard/get/" + id,Result.class);
    }

}
