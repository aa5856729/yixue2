package com.yixue.loxc.system.service;

import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;

public interface TWithdrawService {
    TWithdrawEntity bankmoney(String bankCardId);


    TWithdrawEntity selectbank(String userId, String realname);

}
