package com.yixue.loxc.users.service.impl;

import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.users.dao.UserDao;
import com.yixue.loxc.users.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public TUserAccount login(String username, String password) {

        TUserAccount tUserAccount = userDao.login(username);

        System.out.println(tUserAccount.getPassword());
        System.out.println(password);

        if(tUserAccount != null){
            if(tUserAccount.getPassword().equals(password)){
                return tUserAccount;
            }
        }
        return null;
    }
}
