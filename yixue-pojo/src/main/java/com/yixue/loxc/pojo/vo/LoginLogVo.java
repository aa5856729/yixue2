package com.yixue.loxc.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogVo {
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int loginResult;
    private int currentPage;
    private int accountType;

    public LoginLogVo(String username, Date beginDate, Date endDate, int loginResult, int currentPage) {
        this.username = username;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.loginResult = loginResult;
        this.currentPage = currentPage;
    }
}
