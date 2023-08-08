package com.payment.proportionalpaymentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private List<Order> orders;
    private BigDecimal totalAmount = new BigDecimal(0);
    private BigDecimal increase = new BigDecimal(0);
    private double increasePercent = 0;
    private BigDecimal discount = new BigDecimal(0);
    private double discountPercent = 0;
}
