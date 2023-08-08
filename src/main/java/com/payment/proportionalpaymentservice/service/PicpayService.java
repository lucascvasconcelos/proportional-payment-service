package com.payment.proportionalpaymentservice.service;

import com.payment.proportionalpaymentservice.dto.PicpayRequest;

public interface PicpayService {
    String generatePaymentLink(PicpayRequest request);
}
