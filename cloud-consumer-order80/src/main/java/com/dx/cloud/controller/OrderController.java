package com.dx.cloud.controller;

import com.dx.cloud.entities.CommonResult;
import com.dx.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
//    private  static final String PAYMENT_URL = "http://localhost:8001";
    private  static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    DiscoveryClient discoveryClient;
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/payment/add")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }
}
