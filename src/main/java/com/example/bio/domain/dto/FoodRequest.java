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
    private Long amount;

    @NotNull
    @NumberFormat
    private Long price;

    @NotBlank
    private String category;

    @Builder
    public FoodRequest(String name, Long amount, Long price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public Food toEntity() {
        return Food.builder()
                .name(name)
                .amount(amount)
                .price(price)
                .category(category).build();
    }
}
