package com.payment.proportionalpaymentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.proportionalpaymentservice.dto.PaymentRequest;
import com.payment.proportionalpaymentservice.dto.PaymentResponse;
import com.payment.proportionalpaymentservice.dto.ProportionalPaymentLinkDTO;
import com.payment.proportionalpaymentservice.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import java.math.BigDecimal;
import java.util.Collections;

@WebMvcTest(PaymentRestController.class)
public class PaymentRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentServiceImpl paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCalculateProportionalPayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setOrders(Collections.emptyList());
        PaymentResponse response = new PaymentResponse(Collections.singletonList(new ProportionalPaymentLinkDTO(BigDecimal.TEN, "fake-link", "John")));

        when(paymentService.calculateProportionalPayment(any(PaymentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/payments/calculate-proportional")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.paymentsLink[0].proportionalValueToPay").value(10))
                .andExpect(jsonPath("$.paymentsLink[0].paymentLink").value("fake-link"))
                .andExpect(jsonPath("$.paymentsLink[0].friendName").value("John"));
    }
}
