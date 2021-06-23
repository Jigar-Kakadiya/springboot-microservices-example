package com.example.payment.service.controller;

import com.example.payment.service.model.Payment;
import com.example.payment.service.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Object findPaymentByOrderId(@PathVariable("orderId") Integer orderId) throws JsonProcessingException {
        return paymentService.findPaymentByOrderId(orderId);
    }
}
