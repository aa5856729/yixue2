package com.yixue.loxc.repayment.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TRepayment;
import java.util.List;
import java.util.Map;

public interface RepaymentService {

    public Page getRepaymentList(Map<String, Object> param, Integer currentPage, Integer pageSize);

    public List<TRepayment> getRepaymentByBorrowId(String borrowId);

    public TRepayment getRepaymentById(String id);

    public Integer repay(TRepayment tRepayment);
}
