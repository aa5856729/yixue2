package com.yixue.loxc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.commons.Constants;
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
    TRechargeDao tRechargeDao;

    @Override
    public IPage<TRechargeEntity> selectRecharge(LiuShuiVo liuShuiVo) {


        QueryWrapper<TRechargeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", liuShuiVo.getUserId());
        if (liuShuiVo.getBeginDate() != null && liuShuiVo.getEndDate() != null && liuShuiVo.getCurrentPage() != null) {
            queryWrapper.between("recharge_time", liuShuiVo.getBeginDate(), liuShuiVo.getEndDate());
        }
        int pageindex = 1;
        if (liuShuiVo.getCurrentPage() != null&& liuShuiVo.getCurrentPage() !=0) {
            pageindex = liuShuiVo.getCurrentPage();
        }
        Page page = new Page(pageindex, Constants.DEFAULT_PAGE_SIZE);
        IPage<TRechargeEntity> iPage = tRechargeDao.selectPage(page, queryWrapper);
        return iPage;
    }
}
