package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;
import com.yixue.loxc.user.dao.TRechargeDao;
import com.yixue.loxc.user.service.TRechargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TRechargeServiceImpl implements TRechargeService {

    @Resource
    TRechargeDao  tRechargeDao;

    @Override
    public Page<TRechargeEntity> selectRecharge(LiuShuiVo liuShuiVo) {


        QueryWrapper<TRechargeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",liuShuiVo.getUserId());
        if (liuShuiVo.getBeginDate()!=null&&liuShuiVo.getEndDate()!=null&&liuShuiVo.getCurrentPage()!=null){
            queryWrapper.between("recharge_time",liuShuiVo.getBeginDate(),liuShuiVo.getEndDate());
        }
        List<TRechargeEntity> rechargeEntityList = tRechargeDao.selectList(queryWrapper);
        int pageindex=1;
        if (liuShuiVo.getCurrentPage()!=null){
            pageindex=liuShuiVo.getCurrentPage();
        }
        Page page=new Page(pageindex,10,rechargeEntityList.size());
        page.setListData(rechargeEntityList);
        return page;
    }
}
