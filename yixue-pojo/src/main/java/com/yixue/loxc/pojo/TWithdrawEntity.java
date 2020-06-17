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
public class TWithdrawEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
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

}
