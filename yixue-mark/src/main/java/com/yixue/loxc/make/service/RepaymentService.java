package com.yixue.loxc.make.service;

import com.yixue.loxc.pojo.TRepayment;

import java.util.List;
import java.util.Map;

public interface RepaymentService {

    public List<TRepayment> getTRepamentByList(Map<String,Object> param);
}
