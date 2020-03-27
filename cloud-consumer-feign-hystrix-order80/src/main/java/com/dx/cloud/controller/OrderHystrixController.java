package com.dx.cloud.controller;

import com.dx.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentService;

    @RequestMapping("/ok")
    public String payment_ok(){
        String s = paymentService.payment_ok();
        log.info(s);
        return s;
    }
    @RequestMapping("/timeout")
    @HystrixCommand(fallbackMethod = "payment_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")//线程等待时间3秒 超过就调用fallback方法
    })
    public String payment_timeout(){
        try{TimeUnit.SECONDS.sleep(6);}catch(Exception e){e.printStackTrace();}
        String s = paymentService.payment_timeout();
        log.info(s);
        return s;
    }

    public String payment_handler(){
        return Thread.currentThread().getName() + "\tpayment_handler";
    }
    public String payment_global_fallbackMethod(){
        return " payment_global_fallbackMethod";
    }
}
