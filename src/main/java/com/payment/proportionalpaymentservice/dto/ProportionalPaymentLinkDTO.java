package com.payment.proportionalpaymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProportionalPaymentLinkDTO {
    private BigDecimal proportionalValueToPay;
    private String paymentLink;
    private String friendName;
}
