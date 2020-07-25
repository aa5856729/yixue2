package com.yixue.loxc.bid.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.IdUtil;
import com.yixue.loxc.bid.dao.TBidDao;
import com.yixue.loxc.bid.dao.TBorrowDao;
import com.yixue.loxc.bid.dao.TUserWalletDao;
import com.yixue.loxc.bid.service.TBidService;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBid;
import com.yixue.loxc.pojo.TBidEntity;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.TUserWalletEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.tools.Tool;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TBidServiceImpl implements TBidService {

    @Resource
    private TBidDao tBidDao;

    @Resource
    private TBorrowDao tBorrowDao;

    @Resource
    private TUserWalletDao tUserWalletDao;

    @Override
    public Page getTBidByListPage(Map<String, Object> param,Integer pageNo,Integer pageSize) {

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Integer total = tBidDao.getCount(param);
        Page page = new Page(pageNo,pageSize,total);

        param.put("beginPos",page.getBeginPos());
        param.put("pageSize",page.getPageSize());

        List<TBidEntity> tBidEntityList = tBidDao.getTBidByList(param);

        page.setListData(tBidEntityList);

        return page;
    }

    @Override
    @Transactional
    public Boolean insertBid(TBidEntity tBidEntity) {
        try{
            tBidEntity.setId(IdUtil.simpleUUID());          // 类似 UUID.randomUUID()   添加唯一标识号
            TBorrowEntity tBorrowEntity = tBorrowDao.getTBorrowById(tBidEntity.getBorrowId());
            // 总利润
            Long bidAmout = tBorrowEntity.getCurrentBidAmount() * tBorrowEntity.getYearRate() / 100 / 12 * tBorrowEntity.getRepaymentMonth();

            tBidEntity.setBorrowTitle(tBorrowEntity.getTitle());
            tBidEntity.setBidInterest(tBidEntity.getBidAmount() * tBorrowEntity.getYearRate() / 100 / 12 * tBorrowEntity.getRepaymentMonth());
            tBidEntity.setYearRate(tBorrowEntity.getYearRate());
            tBidEntity.setBorrowState(tBorrowEntity.getBorrowState());
            tBidEntity.setBidTime(new Date());
            tBidEntity.setCreateTime(new Date());
            int number = tBidDao.insertBid(tBidEntity);
            if(number > 0) {
                tBorrowEntity.setBidNum(tBorrowEntity.getBidNum() + 1);
                tBorrowEntity.setCurrentBidAmount(tBorrowEntity.getCurrentBidAmount() + tBidEntity.getBidAmount());
                tBorrowEntity.setCurrentBidInterest(tBorrowEntity.getCurrentBidInterest() + bidAmout);

                if(tBorrowEntity.getCurrentBidAmount().equals(tBorrowEntity.getBorrowAmount())){
                    tBorrowEntity.setBorrowState(30);
                }
                int numberBorrow = tBorrowDao.updateTBorrow(tBorrowEntity);
                if(numberBorrow > 0){
                    // 扣除用户金额
                    TUserWalletEntity tUserWalletEntity = tUserWalletDao.getTUserWalletById(tBidEntity.getBidUserId());
                    tUserWalletEntity.setAvailableAmount(tUserWalletEntity.getAvailableAmount() - tBidEntity.getBidAmount());       // 计算当前可用余额
                    tUserWalletEntity.setInterestPending(tUserWalletEntity.getInterestPending() + bidAmout);                        // 待收利息
                    tUserWalletEntity.setPrincipalPending(tUserWalletEntity.getPrincipalPending() + tBidEntity.getBidAmount());     // 待收本金

                    Integer numberUserWallet = tUserWalletDao.updateTUserWaller(tUserWalletEntity);                                // 修改
                    if(numberUserWallet > 0){
                        return true;
                    }
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
