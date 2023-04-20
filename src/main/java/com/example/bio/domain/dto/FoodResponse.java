package com.example.bio.domain.dto;

import com.example.bio.domain.Food;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
public class FoodResponse {
    private Long id;
    private String name;
    private Long amount;
    private Long price;
    private String category;

    @Builder
    public FoodResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.amount = food.getAmount();
        this.price = food.getPrice();
        this.category = food.getCategory();
    }
}
