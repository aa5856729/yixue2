package com.yixue.loxc.make.service.impl;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.make.dao.BorrowDao;
import com.yixue.loxc.make.service.BorrowService;
import com.yixue.loxc.pojo.TBorrowEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowDao borrowDao;

    @Override
    public Page getTBorrowByList(Map<String, java.lang.Object> param, Integer pageNo, Integer pageSize) {
        Integer totalPage = borrowDao.getTBorrowByCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo,pageSize,totalPage);
        param.put("beginPos",page.getBeginPos());
        param.put("pageSize",page.getPageSize());

        List<TBorrowEntity> tBorrowList = borrowDao.getTBorrowByList(param);

        page.setListData(tBorrowList);

        return page;
    }

    @Override
    public TBorrowEntity getBorrowById(String borrowId) {
        return borrowDao.getBorrowById(borrowId);
    }

    @Override
    public boolean setTBorrow(TBorrowEntity tBorrow) {
        String uuId = null;

        //生成ID，并判断是否重复
        while (true){
            uuId = UUID.randomUUID().toString().substring(0,31);
            if(borrowDao.getBorrowById(uuId) == null){
                break;
            }
        }

        tBorrow.setId(uuId);                                                    //id
        //计算回报总金额
        tBorrow.setTotalInterest((tBorrow.getBorrowAmount() * tBorrow.getYearRate()) / 100 + tBorrow.getBorrowAmount());
        tBorrow.setBorrowType(1);                                               //借款类型
        tBorrow.setBorrowState(10);                                             //申请审核中
        tBorrow.setBidNum(0);                                                   //已投标数量
        tBorrow.setCurrentBidAmount(Integer.toUnsignedLong(0));             //当前已投标金额
        tBorrow.setCurrentBidInterest(Integer.toUnsignedLong(0));           //当前已投标利息
        tBorrow.setApplyTime(new Date());                                      //申请时间
        tBorrow.setCreateTime(new Date());                                     //创建时间

        Integer num = borrowDao.setTBorrow(tBorrow);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTBorrow(String borrowId,String borrowState) {
        TBorrowEntity tBorrowEntity = borrowDao.getBorrowById(borrowId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        //borrowState = borrowState.equals("12") ? "20" : borrowState;
        tBorrowEntity.setBorrowState(Integer.parseInt(borrowState));
        tBorrowEntity.setPublishTime(calendar.getTime());

        calendar.add(calendar.DATE,tBorrowEntity.getBidDays());              //计算招标截止日期
        tBorrowEntity.setBidDeadline(calendar.getTime());

        Integer num = borrowDao.updateTBorrow(tBorrowEntity);
        if(num > 0){
            return true;
        }else{
            return false;
        }
    }
}
