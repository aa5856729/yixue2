package com.yixue.loxc.users.service.impl;

import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.pojo.TUserAccountEntity;
import com.yixue.loxc.users.dao.UserDao;
import com.yixue.loxc.users.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public TUserAccountEntity login(String username, String password) {

        TUserAccountEntity tUserAccount = userDao.login(username);

        if(tUserAccount != null){
            if(tUserAccount.getPassword().equals(password)){
                return tUserAccount;
            }
        }
        return null;
    }
}
