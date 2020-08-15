package com.yixue.loxc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.StatetVo;

import java.util.List;

public interface TRechargeService {
    Page<TRechargeEntity> selectAll(StatetVo statetVo);

    List<TRechargeEntity> getAll(String id);


    int audit(String id, Integer state);
}
