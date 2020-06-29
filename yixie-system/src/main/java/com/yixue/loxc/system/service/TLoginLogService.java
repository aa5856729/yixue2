package com.yixue.loxc.system.service;


import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.vo.LoginLogVo;


import java.util.List;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
public interface TLoginLogService {


    List<TLoginLogEntity> userloglist(Page<TLoginLogEntity> page, LoginLogVo logVo);

    List<TLoginLogEntity> allLog();

}

