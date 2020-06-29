package com.yixue.loxc.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LiuShuiVo {
    private String userId;
    private Date beginDate;
    private Date endDate;
    private Integer currentPage;
}
