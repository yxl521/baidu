package com.jk.controller;

import com.jk.service.BaiduService;
import com.jk.service.BaiduService2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaiduController implements BaiduService2 {

    @Resource
    private BaiduService baiduService;

    @Value("${1904b.api-a.hello}")
    String  name;

    @GetMapping("/getBaiduName")
    public String getBaiduName(){
        System.out.println("调用生产者成功");
        return name;
    }


}
