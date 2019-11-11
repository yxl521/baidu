package com.jk.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value ="provider-staff", fallback = BaiduServiceError.class)
public interface BaiduService2 {

    @GetMapping("/getBaiduName")
    String getBaiduName();
}
