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
    @NotBlank @NumberFormat
    private Long amount;
    @NotBlank @NumberFormat
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
                .name(this.name)
                .amount(this.amount)
                .price(this.price)
                .category(this.category).build();
    }
}
