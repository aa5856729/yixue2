package com.yixue.loxc.make.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBorrow;
import com.yixue.loxc.pojo.TBorrowEntity;

import java.util.Map;


public interface BorrowService {

    /**
     * 条件分页查询
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page getTBorrowByList(Map<String,Object> param,Integer pageNo, Integer pageSize);

    /**
     * 根据id查询
     * @param borrowId
     * @return
     */
    public TBorrowEntity getBorrowById(String borrowId);

    /**
     * 增加
     * @param tBorrow
     * @return
     */
    public boolean setTBorrow(TBorrowEntity tBorrow);

    /**
     * 修改
     * @param
     * @return
     */
    public boolean updateTBorrow(String borrowId,String borrowState);
}
