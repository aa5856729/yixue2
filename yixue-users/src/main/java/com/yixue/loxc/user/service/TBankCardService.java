package com.yixue.loxc.user.service;


import com.yixue.loxc.pojo.entity.TBankCardEntity;

public interface TBankCardService {

    TBankCardEntity getBank(String id);

    Boolean addBank(TBankCardEntity tBankCardEntity);

}
