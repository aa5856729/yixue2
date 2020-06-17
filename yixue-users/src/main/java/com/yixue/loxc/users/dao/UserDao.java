package com.yixue.loxc.users.dao;

import com.yixue.loxc.pojo.TUserAccount;
import com.yixue.loxc.pojo.TUserAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    public TUserAccountEntity login(@Param("username") String username);
}
