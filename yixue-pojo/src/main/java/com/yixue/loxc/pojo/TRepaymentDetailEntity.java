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
public class TRepaymentDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 标的id
	 */
	private String bidId;
	/**
	 * 借款id
	 */
	private String borrowId;
	/**
	 * 还款id
	 */
	private String repaymentId;
	/**
	 * 借款人id
	 */
	private String borrowUserId;
	/**
	 * 投标人id
	 */
	private String bidUserId;
	/**
	 * 借款标题
	 */
	private String borrowTitle;
	/**
	 * 本期还款总金额(利息+本金)(单位：分)
	 */
	private Long totalAmount;
	/**
	 * 本期还款本金(单位；分)
	 */
	private Long principal;
	/**
	 * 本期还款总利息(单位：分)
	 */
	private Long interest;
	/**
	 * 还款期数(第几月还款)
	 */
	private Integer period;
	/**
	 * 本期还款截止日期
	 */
	private Date deadline;
	/**
	 * 还款时间
	 */
	private Date repaymentTime;
	/**
	 * 还款方式
	 */
	private Integer repaymentType;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
