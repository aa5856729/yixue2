package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.entity.TSystemDictionaryEntity;
import com.yixue.loxc.system.dao.DictionaryDao;
import com.yixue.loxc.system.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    DictionaryDao dictionaryDao;

    @Override
    public IPage<TSystemDictionaryEntity> selectKey(String key, int currentPage) {
        QueryWrapper<TSystemDictionaryEntity> queryWrapper = new QueryWrapper<>();
        if (EmptyUtils.isNotEmpty(key)) {
            queryWrapper.like("code", key).or().like("name", key);
        }

        Page<TSystemDictionaryEntity> page = new Page<TSystemDictionaryEntity>(currentPage, Constants.DEFAULT_PAGE_SIZE);

        return dictionaryDao.selectPage(page, queryWrapper);
    }

    @Override
    public int save(String name, String code) {
        TSystemDictionaryEntity dictionaryEntity = new TSystemDictionaryEntity();
        dictionaryEntity.setCode(code);
        dictionaryEntity.setName(name);
        dictionaryEntity.setCreateTime(new Date());
        return dictionaryDao.insert(dictionaryEntity);
    }

    @Override
    public int update(String id, String name, String code) {
        TSystemDictionaryEntity dictionaryEntity = new TSystemDictionaryEntity();
        dictionaryEntity.setId(Integer.parseInt(id));
        dictionaryEntity.setCode(code);
        dictionaryEntity.setName(name);
        return dictionaryDao.deleteById(dictionaryEntity);
    }

    @Override
    public List<TSystemDictionaryEntity> getAll() {
        return dictionaryDao.selectList(null);
    }
}
