package com.yixue.loxc.vo;

import java.sql.Timestamp;
import java.util.Date;

public class QueryObject {

    private String userId;                   //用户id
    private String borrowStates;            //借款状态
    private Integer currentPage;            //当前页数
    private Timestamp beginDate;                 //开始日期
    private Timestamp endDate;                   //结束日期

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowStates() {
        return borrowStates;
    }

    public void setBorrowStates(String borrowStates) {
        this.borrowStates = borrowStates;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
