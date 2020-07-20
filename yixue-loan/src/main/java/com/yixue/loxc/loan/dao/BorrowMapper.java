package com.yixue.loxc.loan.dao;

import com.yixue.loxc.pojo.TBorrowEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowMapper {

    /**
     * 添加借款记录
     * @param tBorrow
     * @return
     */
    public Integer add(TBorrowEntity tBorrow);

    /**
     * 根据条件查询借款记录
     * @param param
     * @return
     */
    public List<TBorrowEntity> getTBorrowList(Map<String,Object> param);

    /**
     * 查询借款记录数
     * @param param
     * @return
     */
    public Integer getTBorrowCount(Map<String,Object> param);

    /**
     * 根据id查询借款信息
     * @param id
     * @return
     */
    public TBorrowEntity getTBorrowById(@Param(value = "id") String id);

    /**
     * 修改借款信息
     * @param tBorrowEntity
     * @return
     */
    public Integer updateTBorrow(TBorrowEntity tBorrowEntity);
}
