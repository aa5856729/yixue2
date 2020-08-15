package com.yixue.loxc.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.pojo.entity.TSystemDictionaryEntity;

import java.util.List;

public interface DictionaryService {

    IPage<TSystemDictionaryEntity> selectKey(String key, int currentPage);

    int save(String name, String code);

    int update(String id, String name, String code);

    List<TSystemDictionaryEntity> getAll();


}
