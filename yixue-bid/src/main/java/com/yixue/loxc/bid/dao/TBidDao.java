package com.yixue.loxc.bid.dao;

import com.yixue.loxc.pojo.TBidEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TBidDao {


    /**
     * 查询投款信息
     * @param param
     * @return
     */
    public List<TBidEntity> getTBidByList(Map<String,Object> param);

    /**
     * 查询信息总数
     * @return
     */
    public Integer getCount(Map<String,Object> param);
}
