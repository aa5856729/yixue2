package com.yixue.loxc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;

public interface DictionaryItemService {

    IPage<TSystemDictionaryItemEntity> selectAll(String key, int indexPage, int pid);

    int save(String parentId, String value, String orderNo);

    int amend(String id, String parentId, String value, String orderNo);
}
