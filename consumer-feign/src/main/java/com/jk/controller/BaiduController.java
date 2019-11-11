package com.jk.controller;

import com.jk.service.BaiduService2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaiduController {

    @Resource
    private BaiduService2 baiduService2;


    @RequestMapping("getBaiduName")
    public String getBaiduName(){
        return baiduService2.getBaiduName();
    }


}
