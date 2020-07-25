package com.yixue.loxc.user.service.impl;

import com.yixue.loxc.pojo.TAccountFlow;
import com.yixue.loxc.user.dao.AccountFlowMapper;
import com.yixue.loxc.user.service.AccountFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountFlowServiceImpl implements AccountFlowService {
    @Resource
    private AccountFlowMapper accountFlowMapper;

    @Override
    public boolean add(TAccountFlow tAccountFlow) {
        if (accountFlowMapper.add(tAccountFlow) > 0){
            return true;
        }
        return false;
    }
}
