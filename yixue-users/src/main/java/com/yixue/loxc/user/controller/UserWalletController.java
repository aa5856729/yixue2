package com.yixue.loxc.user.controller;




import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.yixue.loxc.user.service.UserWalletService;




/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-05 16:11:11
 */
@RestController
@RequestMapping("user/wallet")
@CrossOrigin
public class UserWalletController {
    @Autowired
    private UserWalletService userWalletService;

    /**
     * 显示个人中心初始页面
     * @param id
     * @return
     */
    @PostMapping("/get/{id}")
    public Result<TUserWalletEntity> get(@PathVariable String id){
        TUserWalletEntity userWalletEntity=userWalletService.getwallet(id);
        if (userWalletEntity!=null){
            return new Result(200,"加载成功",userWalletEntity);
        }
        return new Result(202,"无数据");
    }


}
