package com.example.bio.service;

import com.example.bio.domain.*;
import com.example.bio.exception.NotFoundFood;
import com.example.bio.exception.NotFoundMember;
import com.example.bio.exception.NotFoundOrder;
import com.example.bio.repository.FoodRepository;
import com.example.bio.repository.MemberRepository;
import com.example.bio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;

    public Long order(Long memberId, Long foodId, int count) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMember::new);
        Food food = foodRepository.findById(foodId)
                .orElseThrow(NotFoundFood::new);

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
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundOrder::new);
        order.cancel();
    }

    public List<Order> findByNameAndStatus(String name, OrderStatus orderStatus){
        return orderRepository.findQuery(name, orderStatus);
    }
}
