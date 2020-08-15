package com.yixue.loxc.user.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.*;
import com.yixue.loxc.pojo.vo.LiuShuiVo;
import com.yixue.loxc.pojo.vo.UserVo;
import com.yixue.loxc.pojo.vo.WithdrawVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private static final String PAYMENT_URL = "http://localhost:9001";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 登录
     */
    @PostMapping(value = "/login", produces = "application/json")
    public Result<TUserAccountEntity> login(UserVo userVo, HttpServletRequest request) {
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
        userVo.setIp(ip);

        return restTemplate.postForObject(PAYMENT_URL + "/login",userVo,Result.class);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public Result<TUserAccountEntity> register(UserVo userVo){
        return restTemplate.postForObject(PAYMENT_URL + "/register",userVo,Result.class);
    }

    /**
     * 判断用户是否已存在
     */
    @PostMapping("/checkUsername")
    public Boolean checkUsername(String username) {
        return restTemplate.postForObject(PAYMENT_URL + "/checkUsername",username,Boolean.class);
    }

    @PostMapping("/accountflow/query")
    public Result<TRechargeEntity> query(LiuShuiVo liuShuiVo) throws ParseException {
        return restTemplate.postForObject(PAYMENT_URL + "/accountflow/query",liuShuiVo,Result.class);
    }


    /**
     * 绑定银行卡数据初始化
     * @param id
     * @return
     */
    @PostMapping("/bankcard/get/{id}")
    public Result<TWithdrawEntity> get(@PathVariable("id") String id){
        return restTemplate.postForObject(PAYMENT_URL + "/bankcard/get/" + id,id,Result.class);
    }

    @PostMapping("/bankcard/add")
    public Result<TWithdrawEntity> addBank(TBankCardEntity tBankCardEntity){
        return restTemplate.postForObject(PAYMENT_URL + "/bankcard/add",tBankCardEntity,Result.class);
    }

    @GetMapping("/userinfo/get/{id}")
    public Result<TUserInfoEntity> getinfo(@PathVariable("id") String id){
        System.out.println(id);
        return restTemplate.getForObject(PAYMENT_URL + "/userinfo/get/" + id,Result.class);
    }

    @PostMapping("/userinfo/update")
    public Result<TUserInfoEntity> update(TUserInfoEntity userInfoEntity){
        return restTemplate.postForObject(PAYMENT_URL + "/userinfo/update",userInfoEntity,Result.class);
    }

    @PostMapping("/wallet/get/{id}")
    public Result<TUserWalletEntity> getUser(@PathVariable("id") String id){
        System.out.println("=====================================sbsbsbid"+id);
        return restTemplate.postForObject(PAYMENT_URL+"/wallet/get/"+id,id,Result.class);
//        return restTemplate.postForObject(PAYMENT_URL+"/wallet/get/"+id,Result.class);
    }

    @PostMapping("/withdraw/add")
    public Result add(WithdrawVo withdrawVo){
        return restTemplate.postForObject(PAYMENT_URL + "/withdraw/add",withdrawVo,Result.class);
    }

}
