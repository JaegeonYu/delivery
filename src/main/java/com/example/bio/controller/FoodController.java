package com.example.bio.controller;

import com.example.bio.domain.Food;
import com.example.bio.domain.dto.FoodRequest;
import com.example.bio.domain.dto.FoodResponse;
import com.example.bio.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;

    @PostMapping()
    public Long save(@RequestBody @Valid FoodRequest foodRequest) {
        return foodService.save(foodRequest.toEntity());
    }

    @GetMapping()
    public List<FoodResponse> list(){
        return foodService.findAll().stream()
                .map(Food::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{foodId}")
    public FoodResponse get(@PathVariable Long foodId) throws NotFoundException {
        return foodService.findById(foodId).toDto();
    }



}
