package com.example.bio.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrderFood {
    @Id
    @GeneratedValue
    @Column(name = "order_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

}
