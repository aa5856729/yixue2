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
@TableName("t_bank_card")
public class TBankCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 开户人姓名
	 */
	private String realname;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String cardNumber;
	/**
	 * 支行名称
	 */
	private String branchName;
	/**
	 * 可用余额
	 */
	private Long balance;
	/**
	 * 创建日期
	 */
	private Date createTime;

}
