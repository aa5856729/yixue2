package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.pojo.entity.TUserInfoEntity;
import com.yixue.loxc.system.service.SystemDictionaryItemService;
import com.yixue.loxc.user.dao.UserInfoDao;
import org.springframework.stereotype.Service;
import com.yixue.loxc.user.service.UserInfoService;

import javax.annotation.Resource;


@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userInfoDao;

//    @Resource
//    SystemDictionaryItemService systemDictionaryItemService;

    @Override
    public TUserInfoEntity getinfoId(String id) {
        TUserInfoEntity userInfoEntity = userInfoDao.selectById(id);
        return userInfoEntity;
    }

    @Override
    public Integer Userupdate(TUserInfoEntity userInfoEntity) {
        //Integer i =userInfoDao.update(userInfoEntity,new QueryWrapper<UserInfoEntity>().eq("account_id",userInfoEntity.getAccountId()));
        Integer i = userInfoDao.updateById(userInfoEntity);
        return i;
    }
}