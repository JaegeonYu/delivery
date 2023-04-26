package com.example.bio.service;

import com.example.bio.domain.Food;
import com.example.bio.repository.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.crossstore.ChangeSetPersister.*;

@SpringBootTest
@ActiveProfiles("test")
class FoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodRepository foodRepository;
    @BeforeEach
    public void beforeEach(){
        foodRepository.deleteAll();
    }
    @Test
    @DisplayName("음식 작성 테스트")
    public void saveTest() {
        int beforeSize = foodRepository.findAll().size();
        Food food = Food.builder()
                .name("water")
                .amount(2L)
                .price(5000L)
                .category("liquid")
                .build();

        foodService.save(food);
        int afterSize = foodRepository.findAll().size();
        assertEquals(1, afterSize - beforeSize);
    }

    @Test
    @DisplayName("음식 조회 테스트")
    public void findTest() throws NotFoundException {
        Food food = Food.builder()
                .name("water")
                .amount(2L)
                .price(5000L)
                .category("liquid")
                .build();
        foodRepository.save(food);

        Food findFood = foodService.findById(food.getId());

        assertEquals(food.getName(), findFood.getName());
    }

}