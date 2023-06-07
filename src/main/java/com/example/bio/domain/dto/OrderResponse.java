package com.example.bio.domain.dto;

import com.example.bio.domain.*;
import lombok.Builder;

import java.time.LocalDateTime;

public class OrderResponse {
    private Long id;

    private String memberName;

    private int orderPrice;

    private int count;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private DeliveryStatus deliveryStatus;

    private Address address;

    @Builder
    public OrderResponse(Long id, String memberName, int orderPrice, int count, LocalDateTime orderDate, OrderStatus orderStatus, DeliveryStatus deliveryStatus, Address address) {
        this.id = id;
        this.memberName = memberName;
        this.orderPrice = orderPrice;
        this.count = count;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    public static OrderResponse toDto(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .memberName(order.getMember().getName())
                .orderPrice(order.getTotalPrice())
                .count(order.getOrderFoods().get(0).getCount())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .deliveryStatus(order.getDelivery().getDeliveryStatus())
                .address(order.getMember().getAddress())
                .build();
    }
}
