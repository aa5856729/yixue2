package com.yixue.loxc.pojo;


public class TRepaymentDetail {

  private String id;
  private String bidId;
  private String borrowId;
  private String repaymentId;
  private String borrowUserId;
  private String bidUserId;
  private String borrowTitle;
  private long totalAmount;
  private long principal;
  private long interest;
  private long period;
  private java.sql.Timestamp deadline;
  private java.sql.Timestamp repaymentTime;
  private long repaymentType;
  private java.sql.Timestamp createTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getBidId() {
    return bidId;
  }

  public void setBidId(String bidId) {
    this.bidId = bidId;
  }


  public String getBorrowId() {
    return borrowId;
  }

  public void setBorrowId(String borrowId) {
    this.borrowId = borrowId;
  }


  public String getRepaymentId() {
    return repaymentId;
  }

  public void setRepaymentId(String repaymentId) {
    this.repaymentId = repaymentId;
  }


  public String getBorrowUserId() {
    return borrowUserId;
  }

  public void setBorrowUserId(String borrowUserId) {
    this.borrowUserId = borrowUserId;
  }


  public String getBidUserId() {
    return bidUserId;
  }

  public void setBidUserId(String bidUserId) {
    this.bidUserId = bidUserId;
  }


  public String getBorrowTitle() {
    return borrowTitle;
  }

  public void setBorrowTitle(String borrowTitle) {
    this.borrowTitle = borrowTitle;
  }


  public long getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(long totalAmount) {
    this.totalAmount = totalAmount;
  }


  public long getPrincipal() {
    return principal;
  }

  public void setPrincipal(long principal) {
    this.principal = principal;
  }


  public long getInterest() {
    return interest;
  }

  public void setInterest(long interest) {
    this.interest = interest;
  }


  public long getPeriod() {
    return period;
  }

  public void setPeriod(long period) {
    this.period = period;
  }


  public java.sql.Timestamp getDeadline() {
    return deadline;
  }

  public void setDeadline(java.sql.Timestamp deadline) {
    this.deadline = deadline;
  }


  public java.sql.Timestamp getRepaymentTime() {
    return repaymentTime;
  }

  public void setRepaymentTime(java.sql.Timestamp repaymentTime) {
    this.repaymentTime = repaymentTime;
  }


  public long getRepaymentType() {
    return repaymentType;
  }

  public void setRepaymentType(long repaymentType) {
    this.repaymentType = repaymentType;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
