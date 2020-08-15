package com.yixue.loxc.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;

import java.util.List;

public interface TRechargeService {
    IPage<TRechargeEntity> selectRecharge(LiuShuiVo liuShuiVo);

}
