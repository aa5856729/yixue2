package com.yixue.loxc.user.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yixue.loxc.pojo.entity.TUserWalletEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-05 16:11:11
 */
public interface UserWalletService  {


        TUserWalletEntity getwallet(String id);

}

