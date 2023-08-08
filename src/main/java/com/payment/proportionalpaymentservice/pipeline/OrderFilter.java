package com.payment.proportionalpaymentservice.pipeline;

import com.payment.proportionalpaymentservice.dto.Order;
import com.payment.proportionalpaymentservice.dto.PaymentRequest;

public interface OrderFilter {
    public void apply(PaymentRequest paymentRequest, Order order);
}
