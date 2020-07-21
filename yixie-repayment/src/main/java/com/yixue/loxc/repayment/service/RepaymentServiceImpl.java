package com.yixue.loxc.repayment.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.repayment.dao.RepaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Resource
    private RepaymentMapper repaymentMapper;

    @Override
    public Page getRepaymentList(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        Integer totalPage = repaymentMapper.getRepaymentCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo,pageSize,totalPage);
        param.put("beginPos",page.getBeginPos());
        param.put("pageSize",page.getPageSize());

        List<TRepayment> repaymentList = repaymentMapper.getRepaymentList(param);

        page.setListData(repaymentList);

        return page;
    }

    @Override
    public List<TRepayment> getRepaymentByBorrowId(String borrowId) {
        return repaymentMapper.getRepaymentByBorrowId(borrowId);
    }

    @Override
    public TRepayment getRepaymentById(String id) {
        return repaymentMapper.getRepaymentById(id);
    }

    @Override
    public Integer repay(TRepayment tRepayment) {
        //修改用户金额？
        tRepayment.setRepaymentTime(new Timestamp(new Date().getTime()));
        tRepayment.setState(3);
        return repaymentMapper.repay(tRepayment);
    }
}
