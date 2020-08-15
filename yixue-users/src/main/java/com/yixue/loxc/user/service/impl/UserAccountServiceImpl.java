package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.commons.MD5;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TUserAccountEntity;
import com.yixue.loxc.user.dao.UserAccountDao;
import com.yixue.loxc.user.dao.UserInfoDao;
import org.springframework.stereotype.Service;


import com.yixue.loxc.user.service.UserAccountService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    UserAccountDao userAccountDao;

    @Resource
    UserInfoDao userInfoDao;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public Result<TUserAccountEntity> login(String username, String password) {
        QueryWrapper<TUserAccountEntity> queryWrapper = new QueryWrapper<TUserAccountEntity>();
        //对密码进行MD5加密
        String md5password = MD5.getMd5(password, 32);
        System.err.println("MD5==========" + MD5.getRandomCode());
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", md5password);
        Result<TUserAccountEntity> result = new Result<>();
        result.setData(userAccountDao.selectOne(queryWrapper));
        if (result.getData() != null) {
            TUserAccountEntity userAccountEntity = new TUserAccountEntity();
            userAccountEntity.setLastLoginTime(new Date());
            //修改最后的登录时间
            userAccountDao.update(userAccountEntity, new QueryWrapper<TUserAccountEntity>().eq("username", username));
        }
        return result;
    }

    @Override
    public Result<TUserAccountEntity> isuser(String username) {
        TUserAccountEntity accountEntity = userAccountDao.selectOne(new QueryWrapper<TUserAccountEntity>().eq("username", username));
        Result<TUserAccountEntity> result = new Result<>();
        result.setData(accountEntity);
        return result;
    }

    @Override
    public int saveUser(String username, String password) {
        TUserAccountEntity accountEntity = new TUserAccountEntity();
        String id = UUID.randomUUID().toString().substring(0, 31);
        accountEntity.setId(id);
        accountEntity.setUsername(username);
        //对密码进行加密
        accountEntity.setPassword(MD5.getMd5(password, 32));
        accountEntity.setAccountStatus(1);
        accountEntity.setAccountType(1);
        accountEntity.setFillUserinfo(0);
        accountEntity.setCreateTime(new Date());
        int i = userAccountDao.insert(accountEntity);
//        //向userinfo表中添加数据
//        UserInfoEntity userInfoEntity=new UserInfoEntity();
//        userInfoEntity.setAccountId(id);
//        userInfoEntity.setCreateTime(new Date());
//        userInfoDao.insert(userInfoEntity);
        return i;
    }


}