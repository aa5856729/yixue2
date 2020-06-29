package com.yixue.loxc.user.service.impl;


import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.user.dao.TLoginLogDao;
import com.yixue.loxc.user.service.TLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TLoginLogServiceImpl implements TLoginLogService {

    @Resource
    TLoginLogDao tLoginLogDao;

    @Override
    public int addlog(String username, Integer start, String ip) {

        TLoginLogEntity loginLogEntity=new TLoginLogEntity();
        loginLogEntity.setUsername(username);
        loginLogEntity.setLoginResult(start);
        loginLogEntity.setIp(ip);
        loginLogEntity.setLoginTime(new Date());
        loginLogEntity.setAccountType(1);
        loginLogEntity.setCreateTime(new Date());

        int i = tLoginLogDao.insert(loginLogEntity);

        return i;
    }
}
