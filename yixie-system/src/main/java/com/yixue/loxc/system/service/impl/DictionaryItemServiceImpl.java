package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;
import com.yixue.loxc.system.dao.DictionaryItemDao;
import com.yixue.loxc.system.service.DictionaryItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DictionaryItemServiceImpl implements DictionaryItemService {

    @Resource
    DictionaryItemDao dictionaryItemDao;

    @Override
    public IPage<TSystemDictionaryItemEntity> selectAll(String key, int indexPage, int pid) {

        QueryWrapper<TSystemDictionaryItemEntity> wrapper = new QueryWrapper<>();
        if (pid != 0) {
            wrapper.eq("parent_id", pid);
        }
        if (EmptyUtils.isNotEmpty(key)) {
            wrapper.like("parent_id", key).or().like("value", key).or().like("order_no", key);
        }

        Page<TSystemDictionaryItemEntity> page = new Page<>(indexPage, Constants.DEFAULT_PAGE_SIZE);
        return dictionaryItemDao.selectPage(page, wrapper);
    }

    @Override
    public int save(String parentId, String value, String orderNo) {
        TSystemDictionaryItemEntity dictionaryItemEntity = new TSystemDictionaryItemEntity();
        dictionaryItemEntity.setOrderNo(Integer.parseInt(orderNo));
        dictionaryItemEntity.setParentId(Integer.parseInt(parentId));
        dictionaryItemEntity.setValue(value);
        dictionaryItemEntity.setCreateTime(new Date());
        return dictionaryItemDao.insert(dictionaryItemEntity);
    }

    @Override
    public int amend(String id, String parentId, String value, String orderNo) {
        TSystemDictionaryItemEntity dictionaryItemEntity = new TSystemDictionaryItemEntity();
        dictionaryItemEntity.setId(Integer.parseInt(id));
        dictionaryItemEntity.setOrderNo(Integer.parseInt(orderNo));
        dictionaryItemEntity.setParentId(Integer.parseInt(parentId));
        dictionaryItemEntity.setValue(value);
        return dictionaryItemDao.updateById(dictionaryItemEntity);
    }
}
