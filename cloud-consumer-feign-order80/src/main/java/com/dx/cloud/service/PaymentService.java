package com.dx.cloud.service;

import com.dx.cloud.entities.CommonResult;
import com.dx.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@Component
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @RequestMapping("/payment/timeout")
    String timeout();
}
