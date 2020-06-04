package com.yixue.loxc.pojo;


public class TUserAccount {

  private String id;
  private String username;
  private String password;
  private long accountStatus;
  private long accountType;
  private long fillUserinfo;
  private java.sql.Timestamp lastLoginTime;
  private java.sql.Timestamp createTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(long accountStatus) {
    this.accountStatus = accountStatus;
  }


  public long getAccountType() {
    return accountType;
  }

  public void setAccountType(long accountType) {
    this.accountType = accountType;
  }


  public long getFillUserinfo() {
    return fillUserinfo;
  }

  public void setFillUserinfo(long fillUserinfo) {
    this.fillUserinfo = fillUserinfo;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
