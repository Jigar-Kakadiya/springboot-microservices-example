package com.example.payment.service.services;

import com.example.payment.service.model.Payment;
import com.example.payment.service.repositories.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
        Logger logger =LoggerFactory.getLogger(PaymentService.class);
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        logger.info("PaymentServices Request : {}",new ObjectMapper().writeValueAsString(payment));

        System.out.println(payment);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus(paymentProcessing());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"false";
    }

    public Object findPaymentByOrderId(Integer orderId) throws JsonProcessingException {

        Payment payment = paymentRepository.findPaymentByOrderId(orderId);
        if (payment == null){
            return new RuntimeException("payment Not Found"+ payment);
        }
        logger.info("PaymentServices findPaymentByOrderId  : {}",new ObjectMapper().writeValueAsString(payment));

        return payment;
    }
}
