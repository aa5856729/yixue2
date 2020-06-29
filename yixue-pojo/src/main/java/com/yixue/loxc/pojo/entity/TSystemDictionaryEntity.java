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
@TableName("t_system_dictionary")
public class TSystemDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典组id
	 */
	@TableId
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
