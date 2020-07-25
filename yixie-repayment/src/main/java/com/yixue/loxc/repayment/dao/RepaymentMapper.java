package com.yixue.loxc.repayment.dao;

import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.TRepaymentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface RepaymentMapper {

    /**
     * 还款信息分页查询
     * @param param
     * @return
     */
    public List<TRepayment> getRepaymentList(Map<String,Object> param);

    /**
     * 获取还款总记录数
     * @param param
     * @return
     */
    public Integer getRepaymentCount(Map<String,Object> param);

    /**
     * 通过借款id获取还款信息
     * @param borrowId
     * @return
     */
    public List<TRepayment> getRepaymentByBorrowId(@Param("borrowId") String borrowId);

    /**
     * 通过id获取还款信息
     * @param id
     * @return
     */
    public TRepayment getRepaymentById(@Param("id") String id);

    /**
     * 还款方法
     * @param tRepayment
     * @return
     */
    public Integer repay(TRepayment tRepayment);

    /**
     * 添加还款信息
     * @param tRepayment
     * @return
     */
    public Integer add(TRepayment tRepayment);

    /**
     * 明细分页查询
     * @param param
     * @return
     */
    public List<TRepaymentDetail> getRepaymentDetailList(Map<String,Object> param);

    /**
     * 获取明细总记录数
     * @param param
     * @return
     */
    public Integer getRepaymentDetailCount(Map<String,Object> param);

    /**
     * 通过id获取明细
     * @param id
     * @return
     */
    public TRepaymentDetail getRepaymentDetailById(@Param("id") String id);

    /**
     * 添加明细
     * @param tRepaymentDetail
     * @return
     */
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail);
}