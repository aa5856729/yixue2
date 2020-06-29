package com.yixue.loxc.pojo.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageRelust<T> implements Serializable {
     private List<T> listData;
     private Integer totalPage;
     private Integer currentPage;


}
