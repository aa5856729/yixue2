package com.yixue.loxc.pojo;


import java.io.Serializable;

public class TBorrow {

  private String id;
  private String borrowUserId;
  private String borrowUsername;
  private String title;
  private String description;
  private long repaymentType;
  private long borrowType;
  private long borrowState;
  private long borrowAmount;
  private long yearRate;
  private long repaymentMonth;
  private long bidNum;
  private long totalInterest;
  private long currentBidAmount;
  private long currentBidInterest;
  private java.sql.Timestamp bidDeadline;
  private long bidDays;
  private java.sql.Timestamp applyTime;
  private java.sql.Timestamp publishTime;
  private java.sql.Timestamp createTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getBorrowUserId() {
    return borrowUserId;
  }

  public void setBorrowUserId(String borrowUserId) {
    this.borrowUserId = borrowUserId;
  }


  public String getBorrowUsername() {
    return borrowUsername;
  }

  public void setBorrowUsername(String borrowUsername) {
    this.borrowUsername = borrowUsername;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getRepaymentType() {
    return repaymentType;
  }

  public void setRepaymentType(long repaymentType) {
    this.repaymentType = repaymentType;
  }


  public long getBorrowType() {
    return borrowType;
  }

  public void setBorrowType(long borrowType) {
    this.borrowType = borrowType;
  }


  public long getBorrowState() {
    return borrowState;
  }

  public void setBorrowState(long borrowState) {
    this.borrowState = borrowState;
  }


  public long getBorrowAmount() {
    return borrowAmount;
  }

  public void setBorrowAmount(long borrowAmount) {
    this.borrowAmount = borrowAmount;
  }


  public long getYearRate() {
    return yearRate;
  }

  public void setYearRate(long yearRate) {
    this.yearRate = yearRate;
  }


  public long getRepaymentMonth() {
    return repaymentMonth;
  }

  public void setRepaymentMonth(long repaymentMonth) {
    this.repaymentMonth = repaymentMonth;
  }


  public long getBidNum() {
    return bidNum;
  }

  public void setBidNum(long bidNum) {
    this.bidNum = bidNum;
  }


  public long getTotalInterest() {
    return totalInterest;
  }

  public void setTotalInterest(long totalInterest) {
    this.totalInterest = totalInterest;
  }


  public long getCurrentBidAmount() {
    return currentBidAmount;
  }

  public void setCurrentBidAmount(long currentBidAmount) {
    this.currentBidAmount = currentBidAmount;
  }


  public long getCurrentBidInterest() {
    return currentBidInterest;
  }

  public void setCurrentBidInterest(long currentBidInterest) {
    this.currentBidInterest = currentBidInterest;
  }


  public java.sql.Timestamp getBidDeadline() {
    return bidDeadline;
  }

  public void setBidDeadline(java.sql.Timestamp bidDeadline) {
    this.bidDeadline = bidDeadline;
  }


  public long getBidDays() {
    return bidDays;
  }

  public void setBidDays(long bidDays) {
    this.bidDays = bidDays;
  }


  public java.sql.Timestamp getApplyTime() {
    return applyTime;
  }

  public void setApplyTime(java.sql.Timestamp applyTime) {
    this.applyTime = applyTime;
  }


  public java.sql.Timestamp getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(java.sql.Timestamp publishTime) {
    this.publishTime = publishTime;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
