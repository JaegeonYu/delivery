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
    private Long amount;
    @NotNull
    @NumberFormat
    private Long price;

    @Builder
    public FoodChangeRequest(Long amount, Long price) {
        this.amount = amount;
        this.price = price;
    }
}
