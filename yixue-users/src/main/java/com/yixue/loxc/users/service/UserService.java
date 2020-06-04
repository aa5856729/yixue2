package com.yixue.loxc.users.service;

import com.yixue.loxc.pojo.TUserAccount;

public interface UserService {

    public TUserAccount login(String username,String password);
}
