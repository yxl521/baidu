package com.jk.service;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value ="provider-staff", fallback = BaiduServiceError.class)
public interface BaiduService2 {
}
