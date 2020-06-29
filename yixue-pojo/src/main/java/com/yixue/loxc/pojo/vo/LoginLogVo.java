package com.yixue.loxc.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogVo {
    private String username;
    private Date beginDate;
    private Date endDate;
    private int loginResult;
    private int currentPage;
}
