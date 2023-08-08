package com.payment.proportionalpaymentservice.service;

import com.payment.proportionalpaymentservice.dto.*;
import com.payment.proportionalpaymentservice.pipeline.OrderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final String PICPAY_BASE_URL = "https://picpay.me/your_username/";

    @Autowired
    private List<OrderFilter> orderFilters;
    @Autowired
    private PicpayService picpayService;

    public PaymentResponse calculateProportionalPayment(PaymentRequest request) {
        List<ProportionalPaymentLinkDTO> paymentsLink = new ArrayList<>();

        request.getOrders().forEach(o -> {
            for (OrderFilter filter : orderFilters) {
                filter.apply(request, o);
            }

            ProportionalPaymentLinkDTO proportionalPayment = getProportionalPaymentLinkDTO(o);
            paymentsLink.add(proportionalPayment);
        });

        return new PaymentResponse(paymentsLink);
    }

    private ProportionalPaymentLinkDTO getProportionalPaymentLinkDTO(Order o) {
        ProportionalPaymentLinkDTO proportionalPayment = new ProportionalPaymentLinkDTO();
        proportionalPayment.setPaymentLink(generateFakePaymentLink());
        proportionalPayment.setFriendName(o.getFriendName());
        proportionalPayment.setProportionalValueToPay(o.getProportionalOrderPrice());
        return proportionalPayment;
    }

    private String generateFakePaymentLink() {
        String fakePaymentId = UUID.randomUUID().toString();
        return "https://picpay.com/" + fakePaymentId;
    }
}
