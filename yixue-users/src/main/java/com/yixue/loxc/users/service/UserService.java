package com.yixue.loxc.users.service;

import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.pojo.TUserAccountEntity;

public interface UserService {

    public TUserAccountEntity login(String username, String password);
}
