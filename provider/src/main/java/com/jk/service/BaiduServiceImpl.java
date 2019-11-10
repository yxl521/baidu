package com.jk.service;

import com.jk.mapper.BaiduMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaiduServiceImpl implements BaiduService{


    @Resource
    private BaiduMapper baiduMapper;
}
