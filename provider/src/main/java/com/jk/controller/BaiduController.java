package com.jk.controller;

import com.jk.service.BaiduService2;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaiduController implements BaiduService2 {

    @Resource
    private BaiduService2 baiduService2;



}
