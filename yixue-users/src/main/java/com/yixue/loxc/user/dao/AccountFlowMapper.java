package com.yixue.loxc.user.dao;

import com.yixue.loxc.pojo.TAccountFlow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountFlowMapper {
    public Integer add(TAccountFlow tAccountFlow);
}
