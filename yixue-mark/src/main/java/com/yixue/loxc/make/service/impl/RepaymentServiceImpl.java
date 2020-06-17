package com.yixue.loxc.make.service.impl;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.make.dao.RepaymentDao;
import com.yixue.loxc.make.service.RepaymentService;
import com.yixue.loxc.pojo.TRepayment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Resource
    private RepaymentDao repaymentDao;

    @Override
    public List<TRepayment> getTRepamentByList(Map<String, Object> param) {

        return repaymentDao.getTRepamentByList(param);
    }
}
