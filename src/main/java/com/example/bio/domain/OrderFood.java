package com.example.bio.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
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

    @NotNull
    @NumberFormat
    private int orderPrice;

    @NotNull
    @NumberFormat
    private int count;

    @Builder
    public OrderFood(Food food, int orderPrice, int count) {
        this.food = food;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    // 연관관계 편의 메소드
    public void beOrder(Order order) {
        this.order = order;
    }

    // 생성 메소드
    public static OrderFood createOrderFood(Food food, int orderPrice, int count) {
        OrderFood orderFood = OrderFood.builder()
                .food(food)
                .orderPrice(orderPrice)
                .count(count).build();

        food.removeAmount(count);

        return orderFood;
    }

    // 비즈니스 로직
    public void cancel() {
        getFood().addAmount(count);
    }

    public int totalPrice() {
        return getOrderPrice() * getCount();
    }
}
