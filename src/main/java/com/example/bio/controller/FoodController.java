package com.example.bio.controller;

import com.example.bio.domain.dto.FoodRequest;
import com.example.bio.domain.dto.FoodResponse;
import com.example.bio.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService productService;

    @PostMapping()
    public Long save(@RequestBody @Valid FoodRequest foodRequest) {
        return productService.save(foodRequest.toEntity());
    }

    @GetMapping("/{foodId}")
    public FoodResponse get(@PathVariable Long foodId) throws NotFoundException {
        return productService.findById(foodId).toDto();
    }


}
