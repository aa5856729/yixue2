package com.yixue.loxc.make.dao;

import com.yixue.loxc.pojo.TBorrow;
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
    public List<TBorrow> getTBorrowByList(Map<String,Object> param);

    /**
     * 查询总行数
     * @return
     */
    public Integer getTBorrowByCount(Map<String,Object> param);
}
