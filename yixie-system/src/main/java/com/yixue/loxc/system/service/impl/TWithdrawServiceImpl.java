package com.yixue.loxc.system.service.impl;

import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.system.dao.TWithdrawDao;
import com.yixue.loxc.system.service.TWithdrawService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TWithdrawServiceImpl implements TWithdrawService {

    @Resource
    TWithdrawDao tWithdrawDao;

    @Override
    public TWithdrawEntity bankmoney(String bankCardId) {
        TWithdrawEntity cardEntities = tWithdrawDao.selectById(bankCardId);
        return cardEntities;
    }

    @Override
    public TWithdrawEntity selectbank(String userId, String realname) {
        return null;
    }

}
