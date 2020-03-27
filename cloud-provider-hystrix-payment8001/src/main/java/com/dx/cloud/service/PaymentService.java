package com.dx.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_ok(){
        return Thread.currentThread().getName() + "\tpayment_ok";
    }


    @HystrixCommand(fallbackMethod = "payment_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")//线程等待时间3秒 超过就调用fallback方法
    })
    public String payment_timeout(){
//        int timeout = 10/0;  //出现异常会立即调用fallback方法
        int timeout = 3;
        try{TimeUnit.SECONDS.sleep(timeout);}catch(Exception e){e.printStackTrace();}
        return Thread.currentThread().getName() + "\tpayment_timeout";
    }

    // 服务降级
    private String payment_handler(){
        return Thread.currentThread().getName() + "\tpayment_handler";
    }


    //==========服务熔断=======


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器  10s内10次请求 60%都是失败的就跳闸
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("****id 不能为负");
        }
        String serialNum = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\tserialNum:"+serialNum;
    }

    private String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数"+id;
    }
}
