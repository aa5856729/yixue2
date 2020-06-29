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
@TableName("t_account_flow")
public class TAccountFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 账户id
	 */
	private String accountId;
	/**
	 * 金额(单位：分)
	 */
	private Long amount;
	/**
	 * 资金流水类型
	 */
	private Integer flowType;
	/**
	 * (变化后)可用金额(单位：分)
	 */
	private Long availableAmount;
	/**
	 * (变化后)冻结金额(单位：分)
	 */
	private Long freezeAmount;
	/**
	 * 流水说明
	 */
	private String remark;
	/**
	 * 创建日期
	 */
	private Date createTime;

}
