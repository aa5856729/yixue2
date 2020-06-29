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
@TableName("t_repayment")
public class TRepaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 借款id
	 */
	private String borrowId;
	/**
	 * 借款人id
	 */
	private String borrowUserId;
	/**
	 * 借款标题
	 */
	private String borrowTitle;
	/**
	 * 截止日期
	 */
	private Date deadline;
	/**
	 * 还款日期
	 */
	private Date repaymentTime;
	/**
	 * 本期还款总金额(单位：分)
	 */
	private Long totalAmount;
	/**
	 * 本期还款本金(单位：分)
	 */
	private Long principal;
	/**
	 * 本期还款总利息(单位：分)
	 */
	private Long interest;
	/**
	 * 还款期数(第几期)
	 */
	private Integer period;
	/**
	 * 还款状态
	 */
	private Integer state;
	/**
	 * 借款类型
	 */
	private Integer borrowType;
	/**
	 * 还款方式
	 */
	private Integer repaymentType;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
