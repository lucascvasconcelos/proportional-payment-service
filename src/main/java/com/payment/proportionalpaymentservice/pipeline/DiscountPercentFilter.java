package com.payment.proportionalpaymentservice.pipeline;

import com.payment.proportionalpaymentservice.dto.Item;
import com.payment.proportionalpaymentservice.dto.Order;
import com.payment.proportionalpaymentservice.dto.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountPercentFilter implements OrderFilter{
    @Override
    public void apply(PaymentRequest paymentRequest, Order order) {
        BigDecimal initialPriceItems = order.getItems().stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPriceItemsWithDiscountsAndIncreases = order.getProportionalOrderPrice() == null ? initialPriceItems
                : order.getProportionalOrderPrice();

        BigDecimal discountPerFriend = initialPriceItems
                        .multiply(BigDecimal.valueOf(paymentRequest.getDiscountPercent() / 100))
                .setScale(2, RoundingMode.HALF_UP);

        order.setProportionalOrderPrice(totalPriceItemsWithDiscountsAndIncreases.subtract(discountPerFriend));
    }
}
