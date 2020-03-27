package com.dx.cloud.service;

import com.dx.cloud.entities.Payment;
import org.springframework.stereotype.Service;

public interface PaymentServiceImpl {
    int create(Payment payment);

    Payment getById(Long id);
}
