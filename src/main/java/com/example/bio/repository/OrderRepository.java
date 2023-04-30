package com.example.bio.repository;

import com.example.bio.domain.Order;
import com.example.bio.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // JPQL 사용 시에는 DB 기준이 아닌 클래스, 필드에 초점을 맞추고 대소문자 구분이 없는 DB와 달리 구분해야 한다.
    @Query(value = "select o from Order o JOIN FETCH o.member m  WHERE (:name is null or m.name = :name) and (:status is null or o.orderStatus = :status)")
    List<Order> findQuery(@Param("name") String name, @Param("status") OrderStatus orderStatus);
}
