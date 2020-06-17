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
public class TUserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户id
	 */
	private String accountId;
	/**
	 * 肖像图片
	 */
	private String avatar;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 身份证号码
	 */
	private String idCardNumber;
	/**
	 * 手机号码
	 */
	private String phoneNumber;
	/**
	 * 收入等级id
	 */
	private Integer incomeLevelId;
	/**
	 * 婚姻状况id
	 */
	private Integer marriageId;
	/**
	 * 教育背景id
	 */
	private Integer eduBackgroundId;
	/**
	 * 住房情况id
	 */
	private Integer houseConditionId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
