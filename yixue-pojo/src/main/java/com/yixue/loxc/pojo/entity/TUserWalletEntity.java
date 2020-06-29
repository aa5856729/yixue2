package com.yixue.loxc.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-20 11:27:54
 */
@Data
@TableName("t_user_wallet")
public class TUserWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户id
	 */
	@TableId
	private String accountId;
	/**
	 * 可用金额(单位：分)
	 */
	private Long availableAmount;
	/**
	 * 冻结金额(单位：分)
	 */
	private Long freezeAmount;
	/**
	 * 待收利息(单位：分)
	 */
	private Long interestPending;
	/**
	 * 待收本金(单位：分)
	 */
	private Long principalPending;
	/**
	 * 待还金额(单位：分)
	 */
	private Long repaidAmount;
	/**
	 * 信用得分
	 */
	private Integer creditScore;
	/**
	 * 授信额度(单位：分)
	 */
	private Long creditLine;
	/**
	 * 剩余授信额度(单位：分)
	 */
	private Long residualCreditLine;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
