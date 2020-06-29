package com.yixue.loxc.user.service.impl;

import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.user.dao.UserWalletDao;
import org.springframework.stereotype.Service;

import com.yixue.loxc.user.service.UserWalletService;

import javax.annotation.Resource;


@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService{

    @Resource
    UserWalletDao userWalletDao;

    @Override
    public TUserWalletEntity getwallet(String id) {
        TUserWalletEntity walletEntity = userWalletDao.selectById(id);
        return walletEntity;
    }
}