package com.payment.proportionalpaymentservice.controller;

import com.payment.proportionalpaymentservice.dto.PaymentRequest;
import com.payment.proportionalpaymentservice.dto.PaymentResponse;
import com.payment.proportionalpaymentservice.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {
    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping(value = "/calculate-proportional", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> calculateProportionalPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.calculateProportionalPayment(paymentRequest));
    }
}
