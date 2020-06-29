package com.yixue.loxc.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class WithdrawVo {
    private String id;
    /**
     * 提现账户id
     */
    private String userId;
    /**
     * 提现金额(单位：分)
     */
    private Long amount;
    /**
     * 手续费(单位：分)
     */
    private Long fee;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户人姓名
     */
    private String realname;
    /**
     * 银行卡号
     */
    private String cardNumber;
    /**
     * 支行名称
     */
    private String branchName;
    /**
     * 创建时间
     */
    private Date createTime;

    private Long showAmount;
}
