package com.yixue.loxc.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.StatetVo;
import com.yixue.loxc.system.dao.TRechargeDao;
import com.yixue.loxc.system.service.TRechargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TRechargeServiceImpl implements TRechargeService {

    @Resource
    TRechargeDao tRechargeDao;

    @Override
    public Page<TRechargeEntity> selectAll(StatetVo statetVo) {

        QueryWrapper<TRechargeEntity> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("user_id",statetVo.getUserId());

       /* <option value="-1">全部</option>
						    	<option value="1">审核中</option>
						    	<option value="2">审核通过</option>
						    	<option value="0">审核拒绝</option>*/

        if (statetVo.getState()<0){

        }else if (statetVo.getState()>=0){
            queryWrapper.eq("start",statetVo.getState());
        }
        if (statetVo.getEndDate()!=null & statetVo.getBeginDate()!=null){
            queryWrapper.between("recharge_time",statetVo.getBeginDate(),statetVo.getEndDate());
        }

        //查询数据总数量
        List<TRechargeEntity> selectList = tRechargeDao.selectList(queryWrapper);


        Page<TRechargeEntity> page=new Page<>(statetVo.getCurrentPage(),10,selectList.size());
        page.setListData(selectList);


        return page;


    }
}
