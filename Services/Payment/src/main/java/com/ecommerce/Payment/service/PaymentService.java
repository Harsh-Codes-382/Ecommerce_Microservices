package com.ecommerce.Payment.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ecommerce.Payment.mapper.PaymentMapper;
import com.ecommerce.Payment.model.dto.PaymentRequest;
import com.ecommerce.Payment.model.entity.Payment;
import org.springframework.stereotype.Service;
import com.ecommerce.Payment.repository.PaymentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final PaymentMapper paymentMapper;

    public Integer savePayment(@Valid PaymentRequest req) {
        // 1. Save Payment into DB
        Payment payment = paymentRepo.save(paymentMapper.fromPaymentRequestToPayment(req));

        // 2. Send the Notification to notification-Service via (Kafka)

        return payment.getId();
    }
}
