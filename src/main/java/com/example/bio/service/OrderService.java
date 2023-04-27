package com.example.bio.service;

import com.example.bio.domain.*;
import com.example.bio.repository.FoodRepository;
import com.example.bio.repository.MemberRepository;
import com.example.bio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;

    public Long order(Long memberId, Long foodId, int count) throws NotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);
        Food food = foodRepository.findById(foodId)
                .orElseThrow(NotFoundException::new);

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        OrderFood orderFood = OrderFood.createOrderFood(food, food.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderFood);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) throws NotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
        order.cancel();
    }
}
