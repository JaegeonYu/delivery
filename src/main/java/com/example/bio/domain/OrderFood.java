package com.example.bio.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Getter
@NoArgsConstructor
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

    @NotNull @NumberFormat
    private int orderPrice;

    @NotNull @NumberFormat
    private int count;

}
