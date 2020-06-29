package com.yixue.loxc.user.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;

import java.util.List;

public interface TRechargeService {
    Page<TRechargeEntity> selectRecharge(LiuShuiVo liuShuiVo);

}
