package com.example.bio.domain.dto;

import com.example.bio.domain.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class FoodRequest {
    @NotBlank
    private String name;

    @NotNull
    @NumberFormat
    private int amount;

    @NotNull
    @NumberFormat
    private int price;

    @NotBlank
    private String category;

    @Builder
    public FoodRequest(String name, int amount, int price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public static Food toEntity(FoodRequest foodRequest) {
        return Food.builder()
                .name(foodRequest.name)
                .amount(foodRequest.getAmount())
                .price(foodRequest.getPrice())
                .category(foodRequest.getCategory()).build();
    }
}
