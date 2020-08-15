package com.yixue.loxc.pojo.vo.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemVo {
  private String keyword;
  private Integer currentPage;
  private String parentId;
}
