package com.dx.cloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 服务降级工厂
 */
@Component
public class PaymentFeignFactory implements FallbackFactory<PaymentHystrixService> {

    public final PaymentFallback fallback;

    public PaymentFeignFactory(PaymentFallback fallback){
        this.fallback = fallback;
    }
    public PaymentHystrixService create(Throwable throwable) {
        throwable.printStackTrace();
        return fallback;
    }
}
