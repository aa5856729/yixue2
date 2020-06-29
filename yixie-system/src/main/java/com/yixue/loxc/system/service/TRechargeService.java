package com.yixue.loxc.system.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.StatetVo;

public interface TRechargeService {
    Page<TRechargeEntity> selectAll(StatetVo statetVo);

}
