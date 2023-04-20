package com.example.bio.domain;

import com.example.bio.domain.dto.FoodResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long amount;
    private Long price;
    private String category;

    @Builder
    public Food(String name, Long amount, Long price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public FoodResponse toDto() {
        return FoodResponse.builder()
                .food(this).build();
    }
}
