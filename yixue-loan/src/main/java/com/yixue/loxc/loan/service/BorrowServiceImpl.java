package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.loan.dao.BorrowMapper;
import com.yixue.loxc.pojo.*;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.user.service.AccountFlowService;
import com.yixue.loxc.user.service.UserWalletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    UserWalletService userWalletService;

    @Resource
    AccountFlowService accountFlowService;

    @Override
    public Page getTBorrowList(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        Integer totalPage = borrowMapper.getTBorrowCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo, pageSize, totalPage);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());

        List<TBorrowEntity> tBorrowList = borrowMapper.getTBorrowList(param);

        page.setListData(tBorrowList);

        return page;
    }

    @Override
    public TBorrowEntity getTBorrowById(String id) {
        return borrowMapper.getTBorrowById(id);
    }

    @Override
    public boolean add(TBorrowEntity tBorrow) {
        String uuId = null;

        //生成ID，并判断是否重复
        while (true) {
            uuId = UUID.randomUUID().toString().substring(0, 31);
            if (borrowMapper.getTBorrowById(uuId) == null) {
                break;
            }
        }

        tBorrow.setId(uuId);                                                    //id
        //计算回报总金额
        tBorrow.setTotalInterest((tBorrow.getBorrowAmount() * tBorrow.getYearRate()) / 100 / 12 * tBorrow.getRepaymentMonth());
        tBorrow.setBorrowType(1);                                               //借款类型
        tBorrow.setBorrowState(10);                                             //申请审核中
        tBorrow.setBidNum(0);                                                   //已投标数量
        tBorrow.setCurrentBidAmount(Integer.toUnsignedLong(0));             //当前已投标金额
        tBorrow.setCurrentBidInterest(Integer.toUnsignedLong(0));           //当前已投标利息
        tBorrow.setApplyTime(new Date());                                      //申请时间
        tBorrow.setCreateTime(new Date());                                     //创建时间

        Integer num = borrowMapper.add(tBorrow);
        if (num > 0) {
            TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(tBorrow.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() - tBorrow.getBorrowAmount());
            userWalletService.updateUserWallet(tUserWalletEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTBorrow(String borrowId, String borrowState) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(borrowId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //审核拒绝
        if (borrowState.equals("11")) {
            TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tBorrowEntity.getBorrowAmount());
            tBorrowEntity.setBorrowState(11);
            userWalletService.updateUserWallet(tUserWalletEntity);
        } else if (borrowState.equals("12")) {
            //审核通过进入招标状态
            tBorrowEntity.setBorrowState(20);
            tBorrowEntity.setPublishTime(calendar.getTime());
            calendar.add(calendar.DATE, tBorrowEntity.getBidDays());              //计算招标截止日期
            tBorrowEntity.setBidDeadline(calendar.getTime());
        } else {
            tBorrowEntity.setBorrowState(Integer.parseInt(borrowState));
        }

        Integer num = borrowMapper.updateTBorrow(tBorrowEntity);

        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean loanAudit(String borrowId, String borrowState) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(borrowId);                //获取借款信息
        TUserWalletEntity tUserWalletEntity;                                                //获取用户钱包信息
        TAccountFlow tAccountFlow;                                                          //生成账户流水
        List<TBid> bidList;

        //审核拒绝
        if (borrowState.equals("31")) {

            tBorrowEntity.setBorrowState(Integer.parseInt(borrowState));

            //相关投资人收益更新
            bidList = borrowMapper.getBidListByBorrowId(tBorrowEntity.getId());
            for (int i = 0; i < bidList.size(); i++) {
                TBid bid = bidList.get(i);
                tUserWalletEntity = userWalletService.getwallet(bid.getBidUserId());
                tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() + bid.getBidAmount());
                tUserWalletEntity.setFreezeAmount(tUserWalletEntity.getFreezeAmount() - bid.getBidAmount());
                userWalletService.updateUserWallet(tUserWalletEntity);

                //新增一条账户流水
                tAccountFlow = new TAccountFlow();
                tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                tAccountFlow.setAmount(bid.getBidAmount());
                tAccountFlow.setFlowType(50);
                tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                tAccountFlow.setRemark("退款" + tBorrowEntity.getTitle() + "成功，退款金额：" + tAccountFlow.getAmount() / 100 + "元");
                tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
                accountFlowService.add(tAccountFlow);
            }

            tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tBorrowEntity.getBorrowAmount());
            userWalletService.updateUserWallet(tUserWalletEntity);
        } else if (borrowState.equals("32")) {

            tBorrowEntity.setBorrowState(40);

            //借款成功修改账户金额
            tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() + tBorrowEntity.getBorrowAmount());
            tUserWalletEntity.setRepaidAmount(tBorrowEntity.getBorrowAmount() + tBorrowEntity.getTotalInterest());
            userWalletService.updateUserWallet(tUserWalletEntity);

            //新增一条账户流水
            tAccountFlow = new TAccountFlow();
            tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
            tAccountFlow.setAmount(tBorrowEntity.getBorrowAmount());
            tAccountFlow.setFlowType(10);
            tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
            tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
            tAccountFlow.setRemark("借款" + tBorrowEntity.getTitle() + "成功，借款金额：" + tAccountFlow.getAmount() / 100 + "元");
            tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
            accountFlowService.add(tAccountFlow);

            //相关投资人收益更新
            bidList = borrowMapper.getBidListByBorrowId(tBorrowEntity.getId());
            for (int i = 0; i < bidList.size(); i++) {
                TBid bid = bidList.get(i);
                tUserWalletEntity = userWalletService.getwallet(bid.getBidUserId());
                tUserWalletEntity.setFreezeAmount(tUserWalletEntity.getFreezeAmount() - bid.getBidAmount());
                tUserWalletEntity.setPrincipalPending(tUserWalletEntity.getPrincipalPending() + bid.getBidAmount());
                tUserWalletEntity.setInterestPending(tUserWalletEntity.getInterestPending() + bid.getBidInterest());
                userWalletService.updateUserWallet(tUserWalletEntity);

                //新增一条账户流水
                tAccountFlow = new TAccountFlow();
                tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                tAccountFlow.setAmount(bid.getBidAmount());
                tAccountFlow.setFlowType(22);
                tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                tAccountFlow.setRemark("投标" + tBorrowEntity.getTitle() + "成功，投标金额：" + tAccountFlow.getAmount() / 100 + "元");
                tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
                accountFlowService.add(tAccountFlow);
            }

            TRepayment tRepayment = new TRepayment();
            tRepayment.setBorrowId(tBorrowEntity.getId());
            tRepayment.setBorrowUserId(tBorrowEntity.getBorrowUserId());
            tRepayment.setBorrowTitle(tBorrowEntity.getTitle());

            TRepaymentDetail tRepaymentDetail = new TRepaymentDetail();
            tRepaymentDetail.setBorrowId(tBorrowEntity.getId());
            tRepaymentDetail.setBorrowUserId(tBorrowEntity.getBorrowUserId());
            tRepaymentDetail.setBorrowTitle(tBorrowEntity.getTitle());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tBorrowEntity.getPublishTime());

            for (int i = 0; i < tBorrowEntity.getRepaymentMonth() - 1; i++) {
                tRepayment.setId(UUID.randomUUID().toString().substring(0, 31));
                calendar.add(calendar.MONTH, i + 1);
                tRepayment.setDeadline(new Timestamp(calendar.getTime().getTime()));
                //等额本息
                if (tBorrowEntity.getRepaymentType() == 1) {
                    tRepayment.setTotalAmount((tBorrowEntity.getBorrowAmount() + tBorrowEntity.getTotalInterest()) / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPrincipal(tBorrowEntity.getBorrowAmount() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPeriod(i + 1);
                    tRepayment.setState(2);
                    tRepayment.setBorrowType(tBorrowEntity.getBorrowType());
                    tRepayment.setRepaymentType(1);
                    tRepayment.setCreateTime(new Timestamp(new Date().getTime()));

                    borrowMapper.addRepayment(tRepayment);
                } else {
                    //先息后本
                    tRepayment.setTotalAmount(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPrincipal(0);
                    tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPeriod(i + 1);
                    tRepayment.setState(2);
                    tRepayment.setBorrowType(tBorrowEntity.getBorrowType());
                    tRepayment.setRepaymentType(2);
                    tRepayment.setCreateTime(new Timestamp(new Date().getTime()));

                    borrowMapper.addRepayment(tRepayment);
                }

                tRepaymentDetail.setRepaymentId(tRepayment.getId());
                tRepaymentDetail.setRepaymentType(tRepayment.getRepaymentType());
                tRepaymentDetail.setRepaymentTime(tRepayment.getRepaymentTime());
                tRepaymentDetail.setDeadline(tRepayment.getDeadline());

                for (int j = 0; j < bidList.size(); j++) {
                    tRepaymentDetail.setId(UUID.randomUUID().toString().substring(0, 31));
                    tRepaymentDetail.setBidId(bidList.get(j).getId());
                    tRepaymentDetail.setBidUserId(bidList.get(j).getBidUserId());
                    if (tBorrowEntity.getRepaymentType() == 1) {
                        tRepaymentDetail.setPrincipal(bidList.get(j).getBidAmount() / tBorrowEntity.getRepaymentMonth());
                    } else {
                        tRepaymentDetail.setPrincipal(0);
                    }
                    tRepaymentDetail.setInterest(bidList.get(j).getBidInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepaymentDetail.setTotalAmount(tRepaymentDetail.getPrincipal() + tRepaymentDetail.getInterest());
                    tRepaymentDetail.setPeriod(tRepayment.getPeriod());
                    tRepaymentDetail.setCreateTime(new Timestamp(new Date().getTime()));

                    addRepaymentDetail(tRepaymentDetail);
                }
            }

            //最后一个月的还款记录
            tRepayment.setId(UUID.randomUUID().toString().substring(0, 31));
            calendar.add(calendar.MONTH, tBorrowEntity.getRepaymentMonth());
            tRepayment.setDeadline(new Timestamp(calendar.getTime().getTime()));
            if (tBorrowEntity.getRepaymentType() == 1) {
                tRepayment.setPrincipal(tBorrowEntity.getBorrowAmount() / tBorrowEntity.getRepaymentMonth());
            } else {
                tRepayment.setPrincipal(tBorrowEntity.getBorrowAmount());
            }
            tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
            tRepayment.setTotalAmount(tRepayment.getPrincipal() + tRepayment.getInterest());
            tRepayment.setPeriod(tBorrowEntity.getRepaymentMonth());
            tRepayment.setState(2);
            tRepayment.setBorrowType(tBorrowEntity.getBorrowType());
            tRepayment.setRepaymentType(tBorrowEntity.getRepaymentType());
            tRepayment.setCreateTime(new Timestamp(new Date().getTime()));

            borrowMapper.addRepayment(tRepayment);

            tRepaymentDetail.setRepaymentId(tRepayment.getId());
            tRepaymentDetail.setRepaymentType(tRepayment.getRepaymentType());
            tRepaymentDetail.setRepaymentTime(tRepayment.getRepaymentTime());
            tRepaymentDetail.setDeadline(tRepayment.getDeadline());

            for (int j = 0; j < bidList.size(); j++) {
                tRepaymentDetail.setId(UUID.randomUUID().toString().substring(0, 31));
                tRepaymentDetail.setBidId(bidList.get(j).getId());
                tRepaymentDetail.setBidUserId(bidList.get(j).getBidUserId());
                if (tBorrowEntity.getRepaymentType() == 1) {
                    tRepaymentDetail.setPrincipal(bidList.get(j).getBidAmount() / tBorrowEntity.getRepaymentMonth());
                } else {
                    tRepaymentDetail.setPrincipal(bidList.get(j).getBidAmount());
                }
                tRepaymentDetail.setInterest(bidList.get(j).getBidInterest() / tBorrowEntity.getRepaymentMonth());
                tRepaymentDetail.setTotalAmount(tRepaymentDetail.getPrincipal() + tRepaymentDetail.getInterest());
                tRepaymentDetail.setPeriod(tRepayment.getPeriod());
                tRepaymentDetail.setCreateTime(new Timestamp(new Date().getTime()));

                addRepaymentDetail(tRepaymentDetail);
            }
        }

        Integer num = borrowMapper.updateTBorrow(tBorrowEntity);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<TBid> getBidListByBorrowId(String borrowId) {
        return borrowMapper.getBidListByBorrowId(borrowId);
    }

    @Override
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail) {
        return borrowMapper.addRepaymentDetail(tRepaymentDetail);
    }
}
