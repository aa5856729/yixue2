package com.yixue.loxc.bid.service.impl;

import com.yixue.loxc.bid.dao.TBidDao;
import com.yixue.loxc.bid.service.TBidService;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBid;
import com.yixue.loxc.pojo.TBidEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TBidServiceImpl implements TBidService {

    @Resource
    private TBidDao tBidDao;

    @Override
    public Page getTBidByListPage(Map<String, Object> param,Integer pageNo,Integer pageSize) {

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Integer total = tBidDao.getCount(param);
        Page page = new Page(pageNo,pageSize,total);

        param.put("beginPos",page.getBeginPos());
        param.put("pageSize",page.getPageSize());

        List<TBidEntity> tBidEntityList = tBidDao.getTBidByList(param);
        System.out.println(tBidEntityList.size());

        page.setListData(tBidEntityList);

        return page;
    }
}
