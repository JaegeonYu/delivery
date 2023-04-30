package com.example.bio.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderFood> orderFoods = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder
    public Order(Member member, Delivery delivery) {
        this(member, LocalDateTime.now(), OrderStatus.ORDER, delivery);
    }

    private Order(Member member, LocalDateTime orderDate, OrderStatus orderStatus, Delivery delivery) {
        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.delivery = delivery;
    }

    // 연관관계 편의 메서드
    public void conMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void conOrderFood(OrderFood orderFood){
        orderFoods.add(orderFood);
        orderFood.beOrder(this);
    }

    public void conDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.beOrder(this);
    }
    // 생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderFood... orderFoods){
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .build();

        Arrays.stream(orderFoods).forEach(order::conOrderFood);
        return order;
    }
    // 비즈니스 로직
    public void changeStatus(OrderStatus status){
        this.orderStatus = status;
    }
    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMPLETE){
            throw new IllegalArgumentException("배송 완료 상품은 취소 불가"); // TODO 예외처리
        }

        this.changeStatus(OrderStatus.CANCEL);
        for(OrderFood orderFood : orderFoods){
            orderFood.cancel();
        }
    }

    public int getTotalPrice(){
        return orderFoods.stream().mapToInt(OrderFood::totalPrice).sum();
    }
}
