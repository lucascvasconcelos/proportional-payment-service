package com.payment.proportionalpaymentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String friendName;
    private List<Item> items;
    private BigDecimal proportionalOrderPrice;
}
