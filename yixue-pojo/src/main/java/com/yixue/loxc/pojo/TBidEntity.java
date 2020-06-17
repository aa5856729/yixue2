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
public class TBidEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 借款id
	 */
	private String borrowId;
	/**
	 * 借款标题
	 */
	private String borrowTitle;
	/**
	 * 投标金额
	 */
	private Long bidAmount;
	/**
	 * 投标利息
	 */
	private Long bidInterest;
	/**
	 * 年化率
	 */
	private Integer yearRate;
	/**
	 * 借款状态
	 */
	private Integer borrowState;
	/**
	 * 投标人id
	 */
	private String bidUserId;
	/**
	 * 投标人用户名
	 */
	private String bidUsername;
	/**
	 * 投标时间
	 */
	private Date bidTime;
	/**
	 * 创建日期
	 */
	private Date createTime;

}
