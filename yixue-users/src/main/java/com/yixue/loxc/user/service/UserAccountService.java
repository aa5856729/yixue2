package com.yixue.loxc.user.service;


import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TUserAccountEntity;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-05 16:11:11
 */
public interface UserAccountService  {


    Result<TUserAccountEntity> login(String username, String password);


    Result<TUserAccountEntity> isuser(String username);

    int saveUser(String username, String password);

}

