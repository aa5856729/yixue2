package com.yixue.loxc.pojo.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_system_dictionary_item")
public class SystemDictionaryItemEntity implements Serializable {
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
