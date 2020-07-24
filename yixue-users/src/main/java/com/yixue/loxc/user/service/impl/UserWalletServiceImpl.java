package com.yixue.loxc.user.service.impl;

import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.user.dao.UserWalletDao;
import com.yixue.loxc.user.service.UserWalletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService {

    @Resource
    UserWalletDao userWalletDao;

    @Override
    public TUserWalletEntity getwallet(String id) {
        TUserWalletEntity walletEntity = userWalletDao.selectById(id);
        return walletEntity;
    }

    @Override
    public boolean update(TUserWalletEntity tUserWalletEntity) {
        if (userWalletDao.update(tUserWalletEntity) > 0){
            return true;
        }
        return false;
    }
}