package com.example.bio.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class OrderRequest {
    @NotNull
    @NumberFormat
    private Long memberId;

    @NotNull
    @NumberFormat
    private Long foodId;

    @NotNull
    @NumberFormat
    private int count;
    @Builder
    public OrderRequest(Long memberId, Long foodId, int count) {
        this.memberId = memberId;
        this.foodId = foodId;
        this.count = count;
    }
}
