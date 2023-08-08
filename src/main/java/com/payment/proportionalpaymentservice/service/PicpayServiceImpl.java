package com.payment.proportionalpaymentservice.service;

import com.payment.proportionalpaymentservice.dto.PicpayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PicpayServiceImpl implements PicpayService {
    @Value("${picpay.sandbox.url}")
    private String picpaySandboxUrl;

    @Value("${picpay.sandbox.token}")
    private String picpaySandboxToken;

    public String generatePaymentLink(PicpayRequest request) {


        String apiUrl = picpaySandboxUrl + "/ecommerce/public/payments";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-picpay-token", picpaySandboxToken);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST,
                new HttpEntity<>(request, headers), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }
}
