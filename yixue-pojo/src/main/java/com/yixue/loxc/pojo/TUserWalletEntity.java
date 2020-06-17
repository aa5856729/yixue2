package com.yixue.loxc.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
@Data
public class TUserWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户id
	 */
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
