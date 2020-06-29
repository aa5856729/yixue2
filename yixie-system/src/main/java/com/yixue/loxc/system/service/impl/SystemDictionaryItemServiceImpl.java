package com.yixue.loxc.system.service.impl;

import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;
import com.yixue.loxc.system.dao.SystemDictionaryItemDao;
import com.yixue.loxc.system.service.SystemDictionaryItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("SystemDictionaryItemService")
public class SystemDictionaryItemServiceImpl implements SystemDictionaryItemService {

    @Resource
    SystemDictionaryItemDao systemDictionaryItemDao;

    @Override
    public List<TSystemDictionaryItemEntity> getAll() {
        List<TSystemDictionaryItemEntity> itemEntityList = systemDictionaryItemDao.selectList(null);
        return itemEntityList;
    }

    @Override
    public Integer updataItem(TSystemDictionaryItemEntity systemDictionaryItemEntity) {
        int i = systemDictionaryItemDao.updateById(systemDictionaryItemEntity);
        return i;
    }
}