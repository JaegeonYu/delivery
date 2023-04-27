package com.example.bio.service;

import com.example.bio.domain.*;
import com.example.bio.repository.FoodRepository;
import com.example.bio.repository.MemberRepository;
import com.example.bio.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.crossstore.ChangeSetPersister.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderServiceTest {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    public OrderServiceTest(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @BeforeEach
    public void beforeEach(){
        orderRepository.deleteAll();
        memberRepository.deleteAll();
        foodRepository.deleteAll();
    }
    @Test
    @DisplayName("음식주문 테스트")
    void orderFoodTest() throws NotFoundException {
        Member member = createMember();
        Food food = craeteFood("물", 1000, 5, "액체");
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), food.getId(), orderCount);

        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus());
        assertEquals(getOrder.getTotalPrice(), 1000 * orderCount);
        assertEquals(food.getAmount(), 5 - orderCount);
    }

    @Test
    @DisplayName("재고수량 초과 주문 테스트")
    public void foodUnderFlowTest() throws NotFoundException {
        Member member = createMember();
        Food food = craeteFood("pizza", 10000, 10, "양식");
        int orderCount = 11;

        Throwable exception = assertThrows(RuntimeException.class,
                () -> orderService.order(member.getId(), food.getId(), orderCount));

        Assertions.assertEquals(exception.getMessage(), "need more amount");
    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void orderCancelTest() throws NotFoundException {
        Member member = createMember();
        Food food = craeteFood("물", 1000, 5, "액체");
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), food.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(getOrder.getOrderStatus(), OrderStatus.CANCEL);
        assertEquals(food.getAmount(), 5);
    }

    private Food craeteFood(String name, int price, int amount, String category) {
        Food food = Food.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .category(category)
                .build();

        foodRepository.save(food);
        return food;
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("카리나")
                .address(Address.builder()
                        .city("대전")
                        .street("도로명")
                        .zipcode("11111")
                        .build())
                .build();

        memberRepository.save(member);
        return member;
    }
}