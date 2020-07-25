package com.yixue.loxc.bid.dao;

import com.yixue.loxc.pojo.TUserWalletEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserWalletDao {

    /**
     * 根据 accountId 查询
     * @param accountId
     * @return
     */
    public TUserWalletEntity getTUserWalletById(String accountId);

    /**
     * 修改用户金额
     * @param tUserWalletEntity
     * @return
     */
    public Integer updateTUserWaller(TUserWalletEntity tUserWalletEntity);
}
