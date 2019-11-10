package com.jk.service;

import org.springframework.stereotype.Component;

@Component
public class BaiduServiceError implements BaiduService2{

    public void test(){
        System.out.println("测试方法请求");
    }
}
