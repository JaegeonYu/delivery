package com.example.bio.domain.dto;

import com.example.bio.domain.Food;
import lombok.Builder;
import lombok.Data;

@Data
public class FoodRequest {
    private String name;
    private Long amount;
    private Long price;
    private String category;
    @Builder
    public FoodRequest(String name, Long amount, Long price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }
    public Food toEntity(){
        return Food.builder()
                .name(this.name)
                .amount(this.amount)
                .price(this.price)
                .category(this.category).build();
    }
}
