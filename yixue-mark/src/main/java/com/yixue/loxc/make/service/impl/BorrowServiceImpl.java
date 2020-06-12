package com.yixue.loxc.make.service.impl;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.make.dao.BorrowDao;
import com.yixue.loxc.make.service.BorrowService;
import com.yixue.loxc.pojo.TBorrow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowDao borrowDao;

    @Override
    public Page getTBorrowByList(Map<String,Object> param,Integer pageNo, Integer pageSize) {
        Integer totalPage = borrowDao.getTBorrowByCount(param);

        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;

        Page page = new Page(pageNo,pageSize,totalPage);
        param.put("beginPos",page.getBeginPos());
        param.put("pageSize",page.getPageSize());

        List<TBorrow> tBorrowList = borrowDao.getTBorrowByList(param);

        page.setListData(tBorrowList);

        return page;
    }
}
