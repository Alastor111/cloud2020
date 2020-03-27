package com.dx.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE"
//        ,fallback = PaymentFallback.class
        ,fallbackFactory = PaymentFeignFactory.class)
@Component
public interface PaymentHystrixService {

    @RequestMapping("/payment/ok")
    public String payment_ok();
    @RequestMapping("/payment/timeout")
    public String payment_timeout();

}
