package com.yixue.loxc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo {

    private String username;
    private String password;
    private Integer accountType;
    private String ip;
}
