package com.yixue.loxc.bid.dao;

import com.yixue.loxc.pojo.TBorrowEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TBorrowDao {

    public TBorrowEntity getTBorrowById(String id);

    public Integer updateTBorrow(TBorrowEntity tBorrowEntity);
}
