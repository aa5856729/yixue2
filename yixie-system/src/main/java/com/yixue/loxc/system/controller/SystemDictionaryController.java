package com.yixue.loxc.system.controller;



import com.yixue.loxc.system.service.SystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
@RestController
@RequestMapping("/system/aa")
public class SystemDictionaryController {
    /**
     * http://localhost:8080/system/dictionaryItem/getAll
     */
    @Autowired
    private SystemDictionaryService SystemDictionaryService;






}
