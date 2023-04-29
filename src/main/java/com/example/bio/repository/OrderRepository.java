package com.example.bio.repository;

import com.example.bio.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    // MemberName, OrderStatus에 따른 검색 JPQL
}
