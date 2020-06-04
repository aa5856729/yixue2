package com.yixue.loxc.pojo;


public class TLoginLog {

  private long id;
  private String ip;
  private java.sql.Timestamp loginTime;
  private String username;
  private long accountType;
  private long loginResult;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public java.sql.Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(java.sql.Timestamp loginTime) {
    this.loginTime = loginTime;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getAccountType() {
    return accountType;
  }

  public void setAccountType(long accountType) {
    this.accountType = accountType;
  }


  public long getLoginResult() {
    return loginResult;
  }

  public void setLoginResult(long loginResult) {
    this.loginResult = loginResult;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
