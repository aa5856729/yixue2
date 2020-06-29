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
@TableName("t_system_dictionary_item")
public class TSystemDictionaryItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典项id
	 */
	@TableId
	private Integer id;
	/**
	 * 父id
	 */
	private Integer parentId;
	/**
	 * 字典项值
	 */
	private String value;
	/**
	 * 排序号(正序)
	 */
	private Integer orderNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
