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
public class TSystemDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典组id
	 */
	private Integer id;
	/**
	 * 字典组编号
	 */
	private String code;
	/**
	 * 字典组名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
