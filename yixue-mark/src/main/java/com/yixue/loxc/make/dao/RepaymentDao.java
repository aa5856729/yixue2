package com.yixue.loxc.make.dao;

import com.yixue.loxc.pojo.TRepayment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RepaymentDao {

    /**
     * 条件查询
     * @param param
     * @return
     */
    public List<TRepayment> getTRepamentByList(Map<String,Object> param);

}
