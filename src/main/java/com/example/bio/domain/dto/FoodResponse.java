package com.example.bio.domain.dto;

import com.example.bio.domain.Food;
import lombok.Builder;
import lombok.Data;

@Data
public class FoodResponse {
    private Long id;

    private String name;

    private int amount;

    private int price;

    private String category;

    @Builder
    public FoodResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.amount = food.getAmount();
        this.price = food.getPrice();
        this.category = food.getCategory();
    }

    public static FoodResponse toDto(Food food){
        return FoodResponse.builder()
                .food(food)
                .build();
    }
}
