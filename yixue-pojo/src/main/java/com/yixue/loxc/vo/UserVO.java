package com.yixue.loxc.vo;

import lombok.Data;

/**
 * @Author: Liu
 * @Description: TODO
 * @Date: 2020/08/10 14:09
 */
@Data
public class UserVO {

    private String username;
    private String password;
    private String confirmPwd;
    private String ip;

    private String userId;
    private String beginDate;
    private String endDate;
    private String currentPage;

}
