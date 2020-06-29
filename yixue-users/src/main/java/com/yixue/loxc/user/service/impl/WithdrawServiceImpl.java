package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.pojo.vo.WithdrawVo;
import com.yixue.loxc.user.dao.TBankCardDao;
import com.yixue.loxc.user.dao.UserWalletDao;
import com.yixue.loxc.user.dao.WithdrawDao;
import com.yixue.loxc.user.service.WithdrawService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Resource
    WithdrawDao withdrawDao;

    @Resource
    UserWalletDao userWalletDao;

    @Resource
    TBankCardDao tBankCardDao;
    /*
    提现
     */
    @Override
    @Transactional //事务
    public int add(WithdrawVo withdrawVo) {
        TUserWalletEntity tUserWalletEntity=new TUserWalletEntity();

        //账户id条件
        QueryWrapper<TUserWalletEntity> wrapper = new QueryWrapper<TUserWalletEntity>().eq("account_id", withdrawVo.getUserId());
        int i=0;
        //对账户余额查询
        TUserWalletEntity walletEntity = userWalletDao.selectOne(wrapper);

        if (walletEntity.getAvailableAmount()>withdrawVo.getAmount()){  //余额大于提现金额
            tUserWalletEntity.setAvailableAmount(walletEntity.getAvailableAmount()-withdrawVo.getAmount());
            //对账户余额进行减少
            i = userWalletDao.update(tUserWalletEntity,wrapper);
        }else {
            return 0;
        }


        //银行卡条件
        QueryWrapper<TBankCardEntity> queryWrapper = new QueryWrapper<TBankCardEntity>().eq("user_id", withdrawVo.getUserId());
        //查询银行卡现有金额
        TBankCardEntity bankCardEntity = tBankCardDao.selectOne(queryWrapper);

        //对银行卡金额进行增加
        TBankCardEntity tBankCardEntity=new TBankCardEntity();
        tBankCardEntity.setBalance(bankCardEntity.getBalance()+withdrawVo.getAmount());
        int update = tBankCardDao.update(tBankCardEntity, queryWrapper);

        TWithdrawEntity withdrawEntity=new TWithdrawEntity();
        if (i>0&&update>0){
            BeanUtils.copyProperties(withdrawVo,withdrawEntity);
            withdrawEntity.setId(UUID.randomUUID().toString().substring(0,31));
            withdrawEntity.setCreateTime(new Date());
            System.err.println(withdrawEntity);
            //添加记录
            return withdrawDao.insert(withdrawEntity);
        }

        return 0;
    }
}
