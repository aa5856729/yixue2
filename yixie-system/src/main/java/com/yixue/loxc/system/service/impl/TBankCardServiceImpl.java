package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.pojo.vo.RechargeVo;
import com.yixue.loxc.system.dao.TBankCardDao;
import com.yixue.loxc.system.dao.TRechargeDao;
import com.yixue.loxc.system.dao.TUserwalletDao;
import com.yixue.loxc.system.dao.TWithdrawDao;
import com.yixue.loxc.system.service.TBankCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class TBankCardServiceImpl implements TBankCardService {

    @Resource
    TBankCardDao tBankCardDao;

    @Resource
    TRechargeDao tRechargeDao;

    @Resource
    TWithdrawDao tWithdrawDao;

    @Resource
    TUserwalletDao tUserwalletDao;

//    @Override
//    public TBankCardEntity selectbank(String userId, String realname) {
//        TBankCardEntity bankCardEntity=null;
//        if (userId!=null&&realname!=null){
//            bankCardEntity = tBankCardDao.selectOne(new QueryWrapper<TBankCardEntity>().eq("user_id", userId).eq("realname", realname));
//        }
//        return bankCardEntity;
//    }

    @Override
    @Transactional
    public int updataBank(RechargeVo rechargeVo) {
        //对银行卡金额进行修改
        QueryWrapper<TBankCardEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",rechargeVo.getUserId());
        TBankCardEntity bankCardEntity=new TBankCardEntity();
        //查询用户余额
        //审核中审核通过才对金额进行添加
        TBankCardEntity selectbank = bankmoney(rechargeVo.getUserId());

        bankCardEntity.setBalance(selectbank.getBalance()-rechargeVo.getAmount());
        int update = tBankCardDao.update(bankCardEntity, queryWrapper);


        //查询账户余额
        TUserWalletEntity walletEntity = tUserwalletDao.selectById(rechargeVo.getUserId());//通过id进行查询
        //对账户金额进行添加
        TUserWalletEntity tUserWalletEntity=new TUserWalletEntity();
        tUserWalletEntity.setAccountId(rechargeVo.getUserId());  //用户id;
        tUserWalletEntity.setAvailableAmount(walletEntity.getAvailableAmount()+rechargeVo.getAmount());


        tUserwalletDao.updateById(tUserWalletEntity);

        //在添加交易记录
        TRechargeEntity tRecharge=new TRechargeEntity();
        tRecharge.setAmount(rechargeVo.getAmount());
        tRecharge.setUserId(rechargeVo.getUserId());
        tRecharge.setBankCardId(rechargeVo.getBankCardId());
        tRecharge.setUsername(rechargeVo.getUsername());
        tRecharge.setRemark(rechargeVo.getRemark());
        tRecharge.setCreateTime(new Date());
        tRecharge.setRechargeTime(new Date());
        tRecharge.setState(1); //审核状态( 0:审核拒绝  1:审核中  2:审核通过 )
        tRecharge.setTradeNo(UUID.randomUUID().toString().substring(0,31));
        tRecharge.setId(UUID.randomUUID().toString().substring(0,31));

        //充值流水记录
        tRechargeDao.insert(tRecharge);



        return update;

    }

    @Override
    public TBankCardEntity bankmoney(String bankCardId) {

        return tBankCardDao.selectOne(new QueryWrapper<TBankCardEntity>().eq("user_id",bankCardId));
    }
}
