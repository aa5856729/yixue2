package com.yixue.loxc.repayment.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.loan.dao.BorrowMapper;
import com.yixue.loxc.loan.service.BorrowService;
import com.yixue.loxc.pojo.*;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.repayment.dao.RepaymentMapper;
import com.yixue.loxc.user.service.AccountFlowService;
import com.yixue.loxc.user.service.UserWalletService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Resource
    private RepaymentMapper repaymentMapper;

    @Resource
    private BorrowService borrowService;

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
        TRepayment tRepayment = repaymentMapper.getRepaymentById(id);                       //获取还款记录
        TBorrowEntity tBorrowEntity = borrowService.getTBorrowById(tRepayment.getBorrowId());//获取借款记录
        TUserWalletEntity tUserWalletEntity;                                                //获取用户钱包信息
        TAccountFlow tAccountFlow;                                                          //生成账户流水

        tUserWalletEntity = userWalletService.getwallet(userId);
        //还款人的钱包金额大于还款金额才能还款
        if (tUserWalletEntity.getAvailableAmount() > tRepayment.getTotalAmount()) {
            //设置还款日期及状态
            Date date = new Date();
            tRepayment.setRepaymentTime(new Timestamp(date.getTime()));

            //判断还款是否逾期
            if (tRepayment.getDeadline().after(new Timestamp(date.getTime()))) {
                tRepayment.setState(3);
            } else {
                tRepayment.setState(4);
            }

            if (repaymentMapper.repay(tRepayment) > 0) {

                //最后一期还款完成后修改状态为已还清
                boolean result = true;
                List<TRepayment> repaymentList = repaymentMapper.getRepaymentByBorrowId(tBorrowEntity.getId());
                for (int i = 0; i < repaymentList.size(); i++) {
                    if (repaymentList.get(i).getState() == 1 || repaymentList.get(i).getState() == 2) {
                        result = false;
                    }
                }

                //如果没有过逾期
                if (result) {
                    tBorrowEntity.setBorrowState(50);
                    borrowService.updateTBorrow(tBorrowEntity.getId(), tBorrowEntity.getBorrowState().toString());

                    tUserWalletEntity.setCreditScore(tUserWalletEntity.getCreditScore() + 10);      //加十分
                    tUserWalletEntity.setCreditLine(tUserWalletEntity.getCreditLine() + 10 * 100 * 100);  //一分等于100额度
                    tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + 10 * 100 * 100);
                }

                //修改还款账户金额
                tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() - tRepayment.getTotalAmount());
                tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tRepayment.getPrincipal());
                tUserWalletEntity.setRepaidAmount(tUserWalletEntity.getRepaidAmount() - tRepayment.getTotalAmount());

                //添加还款账户流水
                tAccountFlow = new TAccountFlow();
                tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                tAccountFlow.setAmount(tRepayment.getTotalAmount());
                tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                tAccountFlow.setFlowType(30);
                tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                tAccountFlow.setRemark("还款" + tBorrowEntity.getTitle() + "成功，还款金额：" + tAccountFlow.getAmount() / 100 + "元");
                tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));

                userWalletService.updateUserWallet(tUserWalletEntity);
                accountFlowService.add(tAccountFlow);

                //相关投资人收益更新
                List<TBid> bidList = borrowService.getBidListByBorrowId(tRepayment.getBorrowId());
                for (int i = 0; i < bidList.size(); i++) {
                    TBid bid = bidList.get(i);
                    tUserWalletEntity = userWalletService.getwallet(bid.getBidUserId());

                    TRepaymentDetail tRepaymentDetail = repaymentMapper.getTRepaymentDetail(bid.getId(), tRepayment.getPeriod());

                    //修改每位投资人的余额等信息
                    tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() + tRepaymentDetail.getTotalAmount());
                    tUserWalletEntity.setInterestPending(tUserWalletEntity.getInterestPending() - tRepaymentDetail.getInterest());
                    tUserWalletEntity.setPrincipalPending(tUserWalletEntity.getPrincipalPending() - tRepaymentDetail.getPrincipal());
                    userWalletService.updateUserWallet(tUserWalletEntity);

                    //为每位投资人新增一条收款记录
                    tRepaymentDetail.setRepaymentTime(new Timestamp(new Date().getTime()));
                    repaymentMapper.updateRepaymentDetail(tRepaymentDetail);

                    //为每位投资人新增一条账户流水
                    tAccountFlow = new TAccountFlow();
                    tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                    tAccountFlow.setAmount(tRepaymentDetail.getTotalAmount());
                    tAccountFlow.setFlowType(41);
                    tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                    tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                    tAccountFlow.setRemark("回款" + tBorrowEntity.getTitle() + "成功，回款金额：" + tAccountFlow.getAmount() / 100 + "元");
                    tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
                    accountFlowService.add(tAccountFlow);
                }
                return true;
            }
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

    //任务调度器
//    @Scheduled(fixedDelay = 30 * 1000)
//    public void checkTime() {
//        List<TRepayment> repaymentList = repaymentMapper.getRepaymentList(null);
//        for (int i = 0; i < repaymentList.size(); i++) {
//            if (repaymentList.get(i).getRepaymentTime() == null) {
//                if (repaymentList.get(i).getDeadline().before(new Date())) {
//                    repaymentList.get(i).setState(1);
//                    repaymentMapper.updateRepayment(repaymentList.get(i));
//                    TUserWalletEntity tUserWallet = userWalletService.getwallet(repaymentList.get(i).getBorrowUserId());
//                    if (tUserWallet.getCreditScore() > 0) {
//                        tUserWallet.setCreditScore(tUserWallet.getCreditScore() - 1);
//                        tUserWallet.setCreditLine(tUserWallet.getCreditLine() - 100);
//                        tUserWallet.setResidualCreditLine(tUserWallet.getResidualCreditLine() - 100);
//                        System.out.println(tUserWallet);
//                        userWalletService.update(tUserWallet);
//                    }
//                }
//            }
//        }
//    }
}
