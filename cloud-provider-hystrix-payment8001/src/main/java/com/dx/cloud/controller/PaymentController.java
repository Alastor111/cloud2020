package com.dx.cloud.controller;

import com.dx.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping("/ok")
    public String payment_ok(){
        String s = paymentService.payment_ok();
        log.info(s);
        return s;
    }
    @RequestMapping("/timeout")
    public String payment_timeout(){
        String s = paymentService.payment_timeout();
        log.info(s);
        return s;
    }
    @RequestMapping("/breaker/{id}")
    public String breaker(@PathVariable("id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return s;
    }
}
