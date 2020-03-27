package com.dx.cloud.service;


import org.springframework.stereotype.Component;

/**
 * 针对 @FeignClient 标注的接口实现服务降级处理fallback
 */
@Component
public class PaymentFallback implements PaymentHystrixService {
    public String payment_ok() {
        return "PaymentFallback-payment_ok";
    }

    public String payment_timeout() {
        return "PaymentFallback-payment_timeout";
    }
}
