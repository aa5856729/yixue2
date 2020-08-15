package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.pojo.vo.StatetVo;
import com.yixue.loxc.system.dao.TBankCardDao;
import com.yixue.loxc.system.dao.TRechargeDao;
import com.yixue.loxc.system.dao.TUserwalletDao;
import com.yixue.loxc.system.service.TRechargeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TRechargeServiceImpl implements TRechargeService {

    @Resource
    TRechargeDao tRechargeDao;

    @Resource
    TUserwalletDao tUserwalletDao;

    @Resource
    TBankCardDao tBankCardDao;

    @Override
    public Page<TRechargeEntity> selectAll(StatetVo statetVo) {

        QueryWrapper<TRechargeEntity> queryWrapper = new QueryWrapper<>();

        if (EmptyUtils.isNotEmpty(statetVo.getUserId())) {
            queryWrapper.eq("user_id", statetVo.getUserId());
        }

        if (statetVo.getState() >= 0) {
            queryWrapper.eq("state", statetVo.getState());
        }
        if (statetVo.getEndDate() != null & statetVo.getBeginDate() != null) {
            queryWrapper.between("recharge_time", statetVo.getBeginDate(), statetVo.getEndDate());
        }

        //查询数据总数量
        List<TRechargeEntity> selectList = tRechargeDao.selectList(queryWrapper);


        Page<TRechargeEntity> page = new Page<>(statetVo.getCurrentPage(), 10, selectList.size());
        page.setListData(selectList);


        return page;


    }

    @Override
    public List<TRechargeEntity> getAll(String id) {
        return tRechargeDao.selectList(new QueryWrapper<TRechargeEntity>().eq("user_id", id));
    }

    @Override
    @Transactional //事务
    //TODO 未完成
    public int audit(String id, Integer state) {

        //state 审核状态( 0:审核拒绝  1:审核中  2:审核通过 )
        TRechargeEntity tRechargeEntity = new TRechargeEntity();
        tRechargeEntity.setId(id);
        tRechargeEntity.setState(state);
        int id1 = tRechargeDao.updateById(tRechargeEntity);  //修改状态
        TRechargeEntity rechargeEntity = tRechargeDao.selectById(id); //获取userid和金额
        //审核失败
        if (state == 0) {
            TBankCardEntity tBankCardEntity = tBankCardDao.selectOne(new QueryWrapper<TBankCardEntity>().eq("user_id", id));
            TBankCardEntity bankCardEntity = new TBankCardEntity();
            bankCardEntity.setBalance(tBankCardEntity.getBalance() + rechargeEntity.getAmount());
            return tBankCardDao.update(bankCardEntity, new QueryWrapper<TBankCardEntity>().eq("user_id", id));

        }

        //审核通过
        if (state == 2) {
            TUserWalletEntity walletEntity = tUserwalletDao.selectById(rechargeEntity.getUserId());//通过id进行查询
            TUserWalletEntity tUserWalletEntity = new TUserWalletEntity();
            tUserWalletEntity.setAccountId(rechargeEntity.getUserId());  //用户id;
            tUserWalletEntity.setAvailableAmount(walletEntity.getAvailableAmount() + rechargeEntity.getAmount());
            return tUserwalletDao.updateById(tUserWalletEntity);
        }


        return 0;
    }


}
