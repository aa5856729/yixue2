package com.yixue.loxc.system.service;


import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;

import java.util.List;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
public interface SystemDictionaryItemService  {

    List<TSystemDictionaryItemEntity> getAll();

    Integer updataItem(TSystemDictionaryItemEntity systemDictionaryItemEntity);

}

