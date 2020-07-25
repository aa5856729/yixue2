package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.loan.dao.BorrowMapper;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.user.service.UserWalletService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    UserWalletService userWalletService;

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
    public boolean updateTBorrow(String borrowId, String borrowState) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(borrowId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //审核通过进入招标状态
        if (!borrowState.equals("11")) {
            tBorrowEntity.setBorrowState(20);
        } else {
            TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(tBorrowEntity.getBorrowUserId());
            tUserWalletEntity.setResidualCreditLine(tUserWalletEntity.getResidualCreditLine() + tBorrowEntity.getBorrowAmount());
            tBorrowEntity.setBorrowState(Integer.parseInt(borrowState));
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
    public boolean loanAudit(String borrowId, String borrowState) {
        TBorrowEntity tBorrowEntity = borrowMapper.getTBorrowById(borrowId);
        tBorrowEntity.setBorrowState(Integer.parseInt(borrowState));
        Integer num = borrowMapper.updateTBorrow(tBorrowEntity);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }
}
