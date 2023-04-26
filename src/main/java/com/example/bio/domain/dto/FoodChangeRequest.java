package com.example.bio.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class FoodChangeRequest {
    @NotNull
    @NumberFormat
    private int amount;
    @NotNull
    @NumberFormat
    private int price;

    @Builder
    public FoodChangeRequest(int amount, int price) {
        this.amount = amount;
        this.price = price;
    }
}
