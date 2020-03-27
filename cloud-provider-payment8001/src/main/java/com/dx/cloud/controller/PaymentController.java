package com.dx.cloud.controller;

import com.dx.cloud.entities.CommonResult;
import com.dx.cloud.entities.Payment;
import com.dx.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    DiscoveryClient discoveryClient; //服务发现 向外提供信息的。
    @Value("${server.port}")
    private String port;
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        Payment payment = paymentService.getById(id);
        log.info("********获取******");
        return payment == null?new CommonResult<Payment>(400,port+"没有id为"+id+"的payment"):new CommonResult<Payment>(200,port+"成功",payment);
    }
    @PostMapping("/add")
    public CommonResult<Payment> add(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("********插入******");
        return i <= 0 ?new CommonResult<Payment>(400,port+"插入失败"):new CommonResult<Payment>(200,port+"成功",payment);
    }

    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(
                c->{
                    log.info(c);
                    List<ServiceInstance> instances = discoveryClient.getInstances(c);
                    instances.forEach(d ->{
                        log.info(d.getServiceId()+"\t"+d.getInstanceId()+"\t"+d.getHost()+"\t"+d.getPort()+"\t"+d.getUri());
                    });
                }
        );
        return discoveryClient;
    }
    @RequestMapping("/timeout")
    public String timeout(){
        try{TimeUnit.SECONDS.sleep(3);}catch(Exception e){e.printStackTrace();}
        return port;
    }

    @RequestMapping("/port")
    public String port(){
        return port;
    }
}
