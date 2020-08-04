package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.loan.dao.BorrowMapper;
import com.yixue.loxc.pojo.TAccountFlow;
import com.yixue.loxc.pojo.TBid;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.repayment.dao.RepaymentMapper;
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
    private RepaymentMapper repaymentMapper;

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
    public TBorrowEntity getTBorrowByID(String id) {
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
            userWalletService.update(tUserWalletEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTBorrow(TBorrowEntity tBorrow) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(tBorrow.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //审核通过进入招标状态
        if (tBorrow.getBorrowState() != 11) {
            tBorrowEntity.setBorrowState(20);
        } else {
            TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tBorrowEntity.getBorrowAmount());
            tBorrowEntity.setBorrowState(tBorrow.getBorrowState());
            userWalletService.update(tUserWalletEntity);
        }
        tBorrowEntity.setPublishTime(calendar.getTime());

        calendar.add(calendar.DATE, tBorrowEntity.getBidDays());              //计算招标截止日期
        tBorrowEntity.setBidDeadline(calendar.getTime());

        Integer num = borrowMapper.updateTBorrow(tBorrowEntity);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean loanAudit(TBorrowEntity tBorrow) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(tBorrow.getId());                //获取借款信息
        TUserWalletEntity tUserWalletEntity;                                                //获取用户钱包信息
        TAccountFlow tAccountFlow;                                                          //生成账户流水
        List<TBid> bidList;

        //审核拒绝
        if (tBorrow.getBorrowState().equals("31")) {
            //相关投资人收益更新
            bidList = repaymentMapper.getBidListByBorrowId(tBorrowEntity.getId());
            for (int i = 0; i < bidList.size(); i++) {
                TBid bid = bidList.get(i);
                tUserWalletEntity = userWalletService.getwallet(bid.getBidUserId());
                tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() + bid.getBidAmount());
                tUserWalletEntity.setFreezeAmount(tUserWalletEntity.getFreezeAmount() - bid.getBidAmount());
                userWalletService.update(tUserWalletEntity);

                //新增一条账户流水
                tAccountFlow = new TAccountFlow();
                tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                tAccountFlow.setAmount(bid.getBidAmount());
                tAccountFlow.setFlowType(50);
                tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                tAccountFlow.setRemark("退款" + tBorrowEntity.getTitle() + "成功，退款金额：" + tAccountFlow.getAmount() + "元");
                tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
            }

            tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tBorrowEntity.getBorrowAmount());
        } else if (tBorrow.getBorrowState().equals("40")) {
            //借款成功修改账户金额
            tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() + tBorrowEntity.getBorrowAmount());
            tUserWalletEntity.setRepaidAmount(tBorrowEntity.getBorrowAmount() + tBorrowEntity.getTotalInterest());

            //新增一条账户流水
            tAccountFlow = new TAccountFlow();
            tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
            tAccountFlow.setAmount(tBorrowEntity.getBorrowAmount());
            tAccountFlow.setFlowType(10);
            tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
            tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
            tAccountFlow.setRemark("借款" + tBorrowEntity.getTitle() + "成功，借款金额：" + tAccountFlow.getAmount() + "元");
            tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));

            //相关投资人收益更新
            bidList = repaymentMapper.getBidListByBorrowId(tBorrowEntity.getId());
            for (int i = 0; i < bidList.size(); i++) {
                TBid bid = bidList.get(i);
                tUserWalletEntity = userWalletService.getwallet(bid.getBidUserId());

                //新增一条账户流水
                tAccountFlow = new TAccountFlow();
                tAccountFlow.setAccountId(tUserWalletEntity.getAccountId());
                tAccountFlow.setAmount(bid.getBidAmount());
                tAccountFlow.setFlowType(20);
                tAccountFlow.setAvailableAmount(tUserWalletEntity.getAvailableAmount());
                tAccountFlow.setFreezeAmount(tUserWalletEntity.getFreezeAmount());
                tAccountFlow.setRemark("投标" + tBorrowEntity.getTitle() + "成功，投标冻结金额：" + tAccountFlow.getAmount() + "元");
                tAccountFlow.setCreateTime(new Timestamp(new Date().getTime()));
            }

            TRepayment tRepayment = new TRepayment();
            tRepayment.setBorrowId(tBorrowEntity.getId());
            tRepayment.setBorrowUserId(tBorrowEntity.getBorrowUserId());
            tRepayment.setBorrowTitle(tBorrowEntity.getTitle());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tBorrowEntity.getPublishTime());

            if (tBorrowEntity.getBorrowType() == 1) {
                //等额本息
                for (int i = 0; i < tBorrowEntity.getRepaymentMonth(); i++) {
                    tRepayment.setId(UUID.randomUUID().toString().substring(0, 31));
                    calendar.add(calendar.MONTH, i + 1);
                    tRepayment.setDeadline((Timestamp) calendar.getTime());
                    tRepayment.setTotalAmount((tBorrowEntity.getBorrowAmount() + tBorrowEntity.getTotalInterest()) / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPrincipal(tBorrowEntity.getBorrowAmount() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPeriod(i + 1);
                    tRepayment.setState(2);
                    tRepayment.setBorrowType(1);
                    tRepayment.setRepaymentType(1);
                    tRepayment.setCreateTime((Timestamp) new Date());
                    repaymentMapper.add(tRepayment);
                }
            } else {
                //先息后本
                for (int i = 0; i < tBorrowEntity.getRepaymentMonth() - 1; i++) {
                    tRepayment.setId(UUID.randomUUID().toString().substring(0, 31));
                    calendar.add(calendar.MONTH, i + 1);
                    tRepayment.setDeadline((Timestamp) calendar.getTime());
                    tRepayment.setTotalAmount(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPrincipal(0);
                    tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                    tRepayment.setPeriod(i + 1);
                    tRepayment.setState(2);
                    tRepayment.setBorrowType(1);
                    tRepayment.setRepaymentType(2);
                    tRepayment.setCreateTime((Timestamp) new Date());
                    repaymentMapper.add(tRepayment);
                }

                //最后一个月的还款记录
                tRepayment.setId(UUID.randomUUID().toString().substring(0, 31));
                calendar.add(calendar.MONTH, tBorrowEntity.getRepaymentMonth());
                tRepayment.setDeadline((Timestamp) calendar.getTime());
                tRepayment.setTotalAmount(tBorrowEntity.getBorrowAmount() + tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                tRepayment.setPrincipal(tBorrowEntity.getBorrowAmount());
                tRepayment.setInterest(tBorrowEntity.getTotalInterest() / tBorrowEntity.getRepaymentMonth());
                tRepayment.setPeriod(tBorrowEntity.getRepaymentMonth());
                repaymentMapper.add(tRepayment);
            }
        }

        tBorrowEntity.setBorrowState(tBorrow.getBorrowState());

        Integer num = borrowMapper.updateTBorrow(tBorrowEntity);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }
}
