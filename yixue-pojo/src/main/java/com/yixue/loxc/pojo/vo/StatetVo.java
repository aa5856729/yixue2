package com.yixue.loxc.pojo.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StatetVo {

    private String userId;
    private Integer   state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   endDate;
    private Integer currentPage;
}
