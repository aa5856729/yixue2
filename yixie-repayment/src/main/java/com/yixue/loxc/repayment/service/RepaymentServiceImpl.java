package com.yixue.loxc.repayment.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TAccountFlow;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.TRepaymentDetail;
import com.yixue.loxc.pojo.TUserWallet;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.repayment.dao.RepaymentMapper;
import com.yixue.loxc.user.service.AccountFlowService;
import com.yixue.loxc.user.service.UserWalletService;
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

    @Resource
    private UserWalletService userWalletService;

    @Resource
    private AccountFlowService accountFlowService;

    @Override
    public Page getRepaymentList(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        Integer totalPage = repaymentMapper.getRepaymentCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo, pageSize, totalPage);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());

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
    public boolean repay(String id, String userId) {
        TRepayment tRepayment = repaymentMapper.getRepaymentById(id);
        TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(userId);
        TAccountFlow tAccountFlow = new TAccountFlow();

        if (tUserWalletEntity.getAvailableAmount() > tRepayment.getTotalAmount()) {
            //设置还款日期及状态
            Date date = new Date();
            tRepayment.setRepaymentTime(new Timestamp(date.getTime()));
            if (tRepayment.getDeadline().after(new Timestamp(date.getTime()))) {
                tRepayment.setState(3);
            } else {
                tRepayment.setState(4);
            }

            //修改账户金额
            tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() - tRepayment.getTotalAmount());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tRepayment.getPrincipal());
            tUserWalletEntity.setRepaidAmount(tUserWalletEntity.getRepaidAmount() - tRepayment.getTotalAmount());

            //添加账户流水
            tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
            tAccountFlow.setAmount(tRepayment.getTotalAmount());
            tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
            tAccountFlow.setFlowType(30);
            tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
            tAccountFlow.setRemark("还款【测试借款标题】成功，还款金额：" + tAccountFlow.getAmount() + "元");
            tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));

            if (repaymentMapper.repay(tRepayment) > 0) {
                userWalletService.update(tUserWalletEntity);
                accountFlowService.add(tAccountFlow);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(TRepayment tRepayment) {
        if (repaymentMapper.add(tRepayment) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Page getRepaymentDetailList(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        Integer totalPage = repaymentMapper.getRepaymentDetailCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo, pageSize, totalPage);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());

        List<TRepaymentDetail> repaymentDetails = repaymentMapper.getRepaymentDetailList(param);

        page.setListData(repaymentDetails);

        return page;
    }

    @Override
    public TRepaymentDetail getRepaymentDetailById(String id) {
        return repaymentMapper.getRepaymentDetailById(id);
    }

    @Override
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail) {
        return repaymentMapper.addRepaymentDetail(tRepaymentDetail);
    }
}
