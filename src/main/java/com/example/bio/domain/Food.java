package com.example.bio.domain;

import com.example.bio.domain.dto.FoodChangeRequest;
import com.example.bio.domain.dto.FoodResponse;
import com.example.bio.exception.OutOfStuck;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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
    @NotBlank
    private String name;

    @NotNull @NumberFormat
    private int amount;
    @NotNull @NumberFormat
    private int price;

    private String category;


    @Builder
    public Food(String name, int amount, int price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public void patch(FoodChangeRequest foodChangeRequest){
        this.amount = foodChangeRequest.getAmount();
        this.price = foodChangeRequest.getPrice();
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public void removeAmount(int amount){
        int restAmount = this.amount - amount;

            if(restAmount < 0){
            throw new OutOfStuck();
        }

        this.amount = restAmount;
    }
}
