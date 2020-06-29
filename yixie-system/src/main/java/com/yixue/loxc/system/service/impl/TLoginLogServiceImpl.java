package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.vo.LoginLogVo;
import com.yixue.loxc.system.dao.TLoginLogDao;
import com.yixue.loxc.system.service.TLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("tLoginLogService")
public class TLoginLogServiceImpl implements TLoginLogService {

    @Resource
    TLoginLogDao tLoginLogDao;

    @Override
    public List<TLoginLogEntity> userloglist(Page<TLoginLogEntity> page, LoginLogVo logVo) {

        QueryWrapper<TLoginLogEntity> queryWrapper = new QueryWrapper<TLoginLogEntity>();
        queryWrapper.eq("username",logVo.getUsername());
        if (logVo.getBeginDate()!=null && logVo.getEndDate()!=null){  //如果有时间条件就加上  //时间范围查找
            queryWrapper.between("login_time",logVo.getBeginDate(),logVo.getEndDate());
        }
        if (logVo.getLoginResult()==0){ //登录失败状态为0，成功1，全部查询 -1
            queryWrapper.eq("login_result",0);
        } else if (logVo.getLoginResult()==1){
            queryWrapper.eq("login_result",1);
        } else {  //查询全部

        }
        List<TLoginLogEntity> tLoginLogEntities = tLoginLogDao.selectList(queryWrapper);
        System.out.println("List数据:"+tLoginLogEntities);
        page.setListData(tLoginLogEntities);
        return page.getListData();

    }

    @Override
    public List<TLoginLogEntity> allLog() {
        return tLoginLogDao.selectList(null);
    }
}