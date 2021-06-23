package com.example.order.service.config;

import com.example.order.service.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Order order;
    private Double amount;
    private String transactionId;
    private String message;
}
