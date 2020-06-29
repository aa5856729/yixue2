package com.yixue.loxc.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class StatetVo {

    private String userId;
    private int   state;
    private Date  beginDate;
    private Date   endDate;
    private Integer currentPage;
}
