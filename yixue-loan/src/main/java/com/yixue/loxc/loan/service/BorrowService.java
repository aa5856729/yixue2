package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBorrowEntity;
import java.util.List;
import java.util.Map;

public interface BorrowService {

    /**
     * 借款记录分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
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
     * @param tBorrowEntity
     * @return
     */
    public boolean updateTBorrow(TBorrowEntity tBorrowEntity);

    /**
     * 满标审核
     * @param tBorrowEntity
     * @return
     */
    public boolean loanAudit(TBorrowEntity tBorrowEntity);
}
