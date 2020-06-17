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
public class TBorrowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 借款人id
	 */
	private String borrowUserId;
	/**
	 * 借款人用户名
	 */
	private String borrowUsername;
	/**
	 * 借款标题
	 */
	private String title;
	/**
	 * 借款描述
	 */
	private String description;
	/**
	 * 还款类型( 1.等额本息  2.先息后本 )
	 */
	private Integer repaymentType;
	/**
	 * 借款类型( 1.信用贷 2.车贷 3.房贷 )
	 */
	private Integer borrowType;
	/**
	 * 借款状态
	 */
	private Integer borrowState;
	/**
	 * 借款总金额(单位：分)
	 */
	private Long borrowAmount;
	/**
	 * 年化率
	 */
	private Integer yearRate;
	/**
	 * 还款期数
	 */
	private Integer repaymentMonth;
	/**
	 * 已投标数量
	 */
	private Integer bidNum;
	/**
	 * 总回报金额(单位：分)
	 */
	private Long totalInterest;
	/**
	 * 当前已投标金额(单位：分)
	 */
	private Long currentBidAmount;
	/**
	 * 当前已投标利息(单位：分)
	 */
	private Long currentBidInterest;
	/**
	 * 招标截止日期
	 */
	private Date bidDeadline;
	/**
	 * 招标天数
	 */
	private Integer bidDays;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 发标时间
	 */
	private Date publishTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
