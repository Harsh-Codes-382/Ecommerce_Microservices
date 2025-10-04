package com.ecommerce.Payment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.ecommerce.Payment.model.dto.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.Payment.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class Payment {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Integer> Payment_Create_Notify(
            @RequestBody @Valid PaymentRequest req
    ){

        return ResponseEntity.ok(paymentService.savePayment(req));

    }
}
