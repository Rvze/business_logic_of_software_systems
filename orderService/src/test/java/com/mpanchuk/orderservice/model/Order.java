package com.mpanchuk.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "price")
    private int price;

    @Column(name = "discount")
    private int discount;
    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "message")
    private String message;
}
