package com.example.order.service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    private Integer paymentId;
    private String paymentStatus;
    private String transactionId;

    private Integer orderId;
    private Double amount;
}
