package com.yixue.loxc.pojo.vo.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryItemVo {
    private String id;
    private String parentId;
    private String value;
    private String orderNo;
}
