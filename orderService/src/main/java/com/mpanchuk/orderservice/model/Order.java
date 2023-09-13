package com.mpanchuk.orderservice.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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
