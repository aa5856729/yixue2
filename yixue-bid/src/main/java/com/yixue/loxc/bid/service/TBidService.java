package com.yixue.loxc.bid.service;

import com.yixue.loxc.bid.dao.TBidDao;
import com.yixue.loxc.commons.Page;

import javax.annotation.Resource;
import java.util.Map;

public interface TBidService {

    /**
     * 分页查询投款信息
     * @param param
     * @return
     */
    public Page getTBidByListPage(Map<String,Object> param,Integer pageNo,Integer pageSize);
}
