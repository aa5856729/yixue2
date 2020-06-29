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
@TableName("t_recharge")
public class TRechargeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 充值账户id
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 银行卡id
	 */
	private String bankCardId;
	/**
	 * 交易号
	 */
	private String tradeNo;
	/**
	 * 充值金额(单位：分)
	 */
	private Long amount;
	/**
	 * 充值日期
	 */
	private Date rechargeTime;
	/**
	 * 审核状态( 0:审核拒绝  1:审核中  2:审核通过 )
	 */
	private Integer state;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建日期
	 */
	private Date createTime;

}
