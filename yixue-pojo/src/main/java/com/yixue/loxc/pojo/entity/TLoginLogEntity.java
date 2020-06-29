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
@TableName("t_login_log")
public class TLoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 客户端ip地址
	 */
	private String ip;
	/**
	 * 登录时间
	 */
	private Date loginTime;
	/**
	 * 登录用户名
	 */
	private String username;
	/**
	 * 账户类型(1:前台用户, 2:运营人员)
	 */
	private Integer accountType;
	/**
	 * 登录结果(1:成功，0:失败)
	 */
	private Integer loginResult;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
