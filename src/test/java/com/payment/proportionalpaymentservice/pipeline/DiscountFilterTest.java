package com.payment.proportionalpaymentservice.pipeline;

import com.payment.proportionalpaymentservice.dto.Item;
import com.payment.proportionalpaymentservice.dto.Order;
import com.payment.proportionalpaymentservice.dto.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiscountFilterTest {

    private DiscountFilter discountFilter;

    @Mock
    private PaymentRequest paymentRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        discountFilter = new DiscountFilter();
    }

    @Test
    public void testApplyDiscount() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Hamburger", new BigDecimal("25.00")));
        items.add(new Item("Soda", new BigDecimal("25.00")));

        Order order = new Order("John", items, null);

        when(paymentRequest.getDiscount()).thenReturn(new BigDecimal("10"));
        when(paymentRequest.getTotalAmount()).thenReturn(new BigDecimal("50.00"));

        discountFilter.apply(paymentRequest, order);

        assertEquals(new BigDecimal("40.00"), order.getProportionalOrderPrice());
    }
}
