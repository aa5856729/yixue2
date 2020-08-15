package com.yixue.loxc.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.entity.TSystemDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryDao extends BaseMapper<TSystemDictionaryEntity> {

}
