package com.payment.proportionalpaymentservice.service;

import com.payment.proportionalpaymentservice.dto.PaymentRequest;
import com.payment.proportionalpaymentservice.dto.PaymentResponse;

public interface PaymentService {
    public PaymentResponse calculateProportionalPayment(PaymentRequest request);
}
