package com.yixue.loxc.repayment.service;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.TRepaymentDetail;
import java.util.List;
import java.util.Map;

public interface RepaymentService {

    /**
     * 还款信息分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page getRepaymentList(Map<String, Object> param, Integer currentPage, Integer pageSize);

    /**
     * 根据借款id获取还款记录
     * @param borrowId
     * @return
     */
    public List<TRepayment> getRepaymentByBorrowId(String borrowId);

    /**
     * 根据id获取还款记录
     * @param id
     * @return
     */
    public TRepayment getRepaymentById(String id);

    /**
     * 还款方法
     * @param id
     * @param userId
     * @return
     */
    public boolean repay(String id, String userId);

    /**
     * 添加还款信息
     * @param tRepayment
     * @return
     */
    public boolean add(TRepayment tRepayment);

    /**
     * 明细分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page getRepaymentDetailList(Map<String,Object> param, Integer currentPage, Integer pageSize);

    /**
     * 根据id获取明细
     * @param id
     * @return
     */
    public TRepaymentDetail getRepaymentDetailById(String id);

    /**
     * 添加明细
     * @param tRepaymentDetail
     * @return
     */
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail);
}
