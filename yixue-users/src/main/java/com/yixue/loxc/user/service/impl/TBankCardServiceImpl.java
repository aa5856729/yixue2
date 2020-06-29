package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.user.dao.TBankCardDao;
import com.yixue.loxc.user.service.TBankCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TBankCardServiceImpl implements TBankCardService {

    @Resource
    TBankCardDao tBankCardDao;

    @Override
    public TBankCardEntity getBank(String id) {

        return  tBankCardDao.selectOne(new QueryWrapper<TBankCardEntity>().eq("user_id",id));
    }

    @Override
    public Boolean addBank(TBankCardEntity tBankCardEntity) {

        int i = tBankCardDao.insert(tBankCardEntity);
        if (i>0){
            return true;
        }
        return false;
    }
}
