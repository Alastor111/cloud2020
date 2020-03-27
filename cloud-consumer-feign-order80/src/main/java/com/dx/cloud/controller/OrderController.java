package com.dx.cloud.controller;

import com.dx.cloud.entities.CommonResult;
import com.dx.cloud.entities.Payment;
import com.dx.cloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        return paymentService.get(id);
    }
    @RequestMapping("/payment/timeout")
    public String timeout(){
        return paymentService.timeout();
    }
}
