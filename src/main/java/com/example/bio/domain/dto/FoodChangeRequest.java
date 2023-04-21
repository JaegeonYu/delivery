package com.example.bio.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class FoodChangeRequest {
    @NotBlank @NumberFormat
    private Long amount;
    @NotBlank @NumberFormat
    private Long price;
    @Builder
    public FoodChangeRequest(Long amount, Long price) {
        this.amount = amount;
        this.price = price;
    }
}
