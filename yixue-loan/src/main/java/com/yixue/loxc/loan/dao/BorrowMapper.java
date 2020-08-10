package com.yixue.loxc.loan.dao;

import com.yixue.loxc.pojo.TBid;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.TRepaymentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowMapper {

    /**
     * 添加借款记录
     *
     * @param tBorrow
     * @return
     */
    public Integer add(TBorrowEntity tBorrow);

    /**
     * 根据条件查询借款记录
     *
     * @param param
     * @return
     */
    public List<TBorrowEntity> getTBorrowList(Map<String, Object> param);

    /**
     * 查询借款记录数
     *
     * @param param
     * @return
     */
    public Integer getTBorrowCount(Map<String, Object> param);

    /**
     * 根据id查询借款信息
     *
     * @param id
     * @return
     */
    public TBorrowEntity getTBorrowById(@Param("id") String id);

    /**
     * 修改借款信息
     *
     * @param tBorrowEntity
     * @return
     */
    public Integer updateTBorrow(TBorrowEntity tBorrowEntity);

    /**
     * 根据借款id获取标的列表
     *
     * @param borrowId
     * @return
     */
    public List<TBid> getBidListByBorrowId(@Param("borrowId") String borrowId);

    /**
     * 添加还款记录
     *
     * @param tRepayment
     * @return
     */
    public Integer addRepayment(TRepayment tRepayment);

    /**
     * 添加明细
     *
     * @param tRepaymentDetail
     * @return
     */
    public Integer addRepaymentDetail(TRepaymentDetail tRepaymentDetail);
}
