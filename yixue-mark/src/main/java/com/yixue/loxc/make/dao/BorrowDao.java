package com.yixue.loxc.make.dao;

import com.yixue.loxc.pojo.TBorrow;
import com.yixue.loxc.pojo.TBorrowEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowDao {

    /**
     * 分页查询
     * @param param 传递的参数条件
     * @param
     * @return
     */
    public List<TBorrowEntity> getTBorrowByList(Map<String,Object> param);

    /**
     * 查询总行数
     * @return
     */
    public Integer getTBorrowByCount(Map<String,Object> param);

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
    public Integer setTBorrow(TBorrowEntity tBorrow);

    /**
     * 修改
     * @param tBorrowEntity
     * @return
     */
    public Integer updateTBorrow(TBorrowEntity tBorrowEntity);
}
