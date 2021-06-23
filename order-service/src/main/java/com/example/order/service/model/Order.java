package com.example.order.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_DB")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private Integer id;
    private String name;
    private Integer qty;
    private Double price;
}
