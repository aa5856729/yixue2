package com.yixue.loxc.pojo;


public class TRepayment {

  private String id;
  private String borrowId;
  private String borrowUserId;
  private String borrowTitle;
  private java.sql.Timestamp deadline;
  private java.sql.Timestamp repaymentTime;
  private long totalAmount;
  private long principal;
  private long interest;
  private long period;
  private long state;
  private long borrowType;
  private long repaymentType;
  private java.sql.Timestamp createTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getBorrowId() {
    return borrowId;
  }

  public void setBorrowId(String borrowId) {
    this.borrowId = borrowId;
  }


  public String getBorrowUserId() {
    return borrowUserId;
  }

  public void setBorrowUserId(String borrowUserId) {
    this.borrowUserId = borrowUserId;
  }


  public String getBorrowTitle() {
    return borrowTitle;
  }

  public void setBorrowTitle(String borrowTitle) {
    this.borrowTitle = borrowTitle;
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


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public long getBorrowType() {
    return borrowType;
  }

  public void setBorrowType(long borrowType) {
    this.borrowType = borrowType;
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
