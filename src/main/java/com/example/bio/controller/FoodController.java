package com.example.bio.controller;

import com.example.bio.domain.Food;
import com.example.bio.domain.dto.FoodChangeRequest;
import com.example.bio.domain.dto.FoodRequest;
import com.example.bio.domain.dto.FoodResponse;
import com.example.bio.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;

    @PostMapping()
    public ResponseEntity<Long> postFood(@RequestBody @Valid FoodRequest foodRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(foodService.save(foodRequest.toEntity()));
    }

    @GetMapping()
    public ResponseEntity<List<FoodResponse>> getFoods(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.findAll().stream()
                .map(Food::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodResponse> getFood(@PathVariable Long foodId) throws NotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.findById(foodId).toDto());
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Long> deleteFood(@PathVariable Long foodId) throws NotFoundException {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(foodService.delete(foodId));
    }

    @PatchMapping("/{foodId}")
    public ResponseEntity<Long> patchFood(@PathVariable Long foodId, @RequestBody FoodChangeRequest foodChangeRequest) throws NotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.patch(foodId, foodChangeRequest));
    }

}
