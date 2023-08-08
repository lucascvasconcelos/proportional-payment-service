package com.payment.proportionalpaymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicpayRequest {
    private String referenceId;
    private Double value;
    private String callbackUrl;
}
