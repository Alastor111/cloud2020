package com.dx.cloud.service;

import com.dx.cloud.dao.PaymentDao;
import com.dx.cloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class PaymentService implements PaymentServiceImpl{
    @Resource
    private PaymentDao paymentDao;
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }
}
