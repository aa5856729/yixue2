package com.yixue.loxc.repayment.dao;

import com.yixue.loxc.pojo.TRepayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface RepaymentMapper {

    public List<TRepayment> getRepaymentList(Map<String,Object> param);

    public Integer getRepaymentCount(Map<String,Object> param);

    public List<TRepayment> getRepaymentByBorrowId(@Param("borrowId") String borrowId);

    public TRepayment getRepaymentById(@Param("id") String id);

    public Integer repay(TRepayment tRepayment);
}
