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
public class TSystemDictionaryItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典项id
	 */
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
