package com.yixue.loxc.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class QueryObject {

    private String userId;                       //用户id
    private String borrowStates;                //借款状态
    private Integer currentPage;                //当前页数
    private Timestamp beginDate;                //开始日期
    private Timestamp endDate;                  //结束日期
    private String borrowId;                    //借款id
}
