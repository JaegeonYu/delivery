package com.example.bio.controller;

import com.example.bio.domain.OrderStatus;
import com.example.bio.domain.dto.OrderRequest;
import com.example.bio.domain.dto.OrderResponse;
import com.example.bio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<Long> postOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.order(orderRequest.getMemberId(),
                        orderRequest.getFoodId(),
                        orderRequest.getCount()));
    }

    @GetMapping("/{orderID}")
    public ResponseEntity cancelOrder(@PathVariable Long orderID) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .build(); // TODO 빈값을 보낼 때 메소드 리턴타입 제네릭 지정?
    }
    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> findByNameAndStatus(@RequestParam String name, @RequestParam OrderStatus orderStatus){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findByNameAndStatus(name, orderStatus).stream()
                        .map(OrderResponse::toDto).collect(Collectors.toList()));
    }
}
