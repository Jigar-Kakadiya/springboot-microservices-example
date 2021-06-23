package com.example.order.service.services;

import com.example.order.service.config.Payment;
import com.example.order.service.config.TransactionRequest;
import com.example.order.service.config.TransactionResponse;
import com.example.order.service.model.Order;
import com.example.order.service.repositories.OrderRepositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepositories orderRepositories;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse bookOrder(TransactionRequest request) throws JsonProcessingException {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setAmount(order.getPrice());
        payment.setOrderId(order.getId());
        logger.info("OrderServices Request : {}",new ObjectMapper().writeValueAsString(request));
        System.out.println(payment);
        System.out.println(order);
        Payment payment1 = restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);
        logger.info("payment service response from OrderServices rest call : {}",new ObjectMapper().writeValueAsString(payment1));

        assert payment1 != null;
        String message = payment1.getPaymentStatus().equals("success")?"payment and order is successfully completed":"payment is mot completed ! please try again";
        orderRepositories.save(order);
        return new TransactionResponse(order,payment1.getAmount(),payment1.getTransactionId(),message);
    }
}

