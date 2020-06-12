package com.yixue.loxc.make.service;

import com.yixue.loxc.commons.Page;

import java.util.Map;


public interface BorrowService {

    public Page getTBorrowByList(Map<String,Object> param,Integer pageNo, Integer pageSize);
}
