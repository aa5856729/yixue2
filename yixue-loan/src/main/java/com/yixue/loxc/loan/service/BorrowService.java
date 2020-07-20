package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBorrowEntity;
import java.util.List;
import java.util.Map;

public interface BorrowService {


    public Page getTBorrowList(Map<String,Object> param, Integer currentPage, Integer pageSize);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public TBorrowEntity getTBorrowByID(String id);

    /**
     * 增加
     * @param tBorrow
     * @return
     */
    public boolean add(TBorrowEntity tBorrow);

    /**
     * 修改
     * @param
     * @return
     */
    public boolean updateTBorrow(String borrowId,String borrowState);

    public boolean loanAudit(String borrowId,String borrowState);
}
