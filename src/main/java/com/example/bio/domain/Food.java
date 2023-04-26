package com.example.bio.domain;

import com.example.bio.domain.dto.FoodChangeRequest;
import com.example.bio.domain.dto.FoodResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
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

    public void patch(FoodChangeRequest foodChangeRequest){
        this.amount = foodChangeRequest.getAmount();
        this.price = foodChangeRequest.getPrice();
    }
}
