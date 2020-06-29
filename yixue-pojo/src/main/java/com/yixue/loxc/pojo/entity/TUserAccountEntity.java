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
@TableName("t_user_account")
public class TUserAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户id
	 */
	@TableId
	private String id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态( 0:禁用, 1:正常 )
	 */
	private Integer accountStatus;
	/**
	 * 账户类型( 1:普通用户, 2:运营人员 )
	 */
	private Integer accountType;
	/**
	 * 是否完善个人信息( 0:未完善, 1:已完善 )
	 */
	private Integer fillUserinfo;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
