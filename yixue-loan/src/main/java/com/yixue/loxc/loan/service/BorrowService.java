package com.yixue.loxc.loan.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TBid;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.TRepaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BorrowService {

    /**
     * 借款记录分页查询
     *
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page getTBorrowList(Map<String, Object> param, Integer currentPage, Integer pageSize);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public TBorrowEntity getTBorrowById(String id);

    /**
     * 增加
     *
     * @param tBorrow
     * @return
     */
    public boolean add(TBorrowEntity tBorrow);

    /**
     * 修改
     *
     * @param borrowId
     * @param borrowState
     * @return
     */
    public boolean updateTBorrow(String borrowId, String borrowState);

    /**
     * 满标审核
     *
     * @param borrowId
     * @param borrowState
     * @return
     */
    public boolean loanAudit(String borrowId, String borrowState);

    /**
     * 根据借款id获取标的列表
     *
     * @param borrowId
     * @return
     */
    public List<TBid> getBidListByBorrowId(String borrowId);

    /**
     * 添加明细
     *
     * @param tRepaymentDetail
     * @return
     */
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail);
}
