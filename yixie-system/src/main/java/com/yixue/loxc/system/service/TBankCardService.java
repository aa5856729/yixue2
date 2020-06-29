package com.yixue.loxc.system.service;

import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.pojo.vo.RechargeVo;

import java.util.List;

public interface TBankCardService {


    //TBankCardEntity selectbank(String userId, String realname);

    int updataBank(RechargeVo rechargeVo);


    TBankCardEntity bankmoney(String bankCardId);

}
