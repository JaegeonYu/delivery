package com.example.bio.service;

import com.example.bio.domain.Food;
import com.example.bio.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Service
public class FoodService {
    private final FoodRepository foodRepositoryRepository;
    @Autowired
    public FoodService(FoodRepository productRepository) {
        this.foodRepositoryRepository = productRepository;
    }

    public Food findById(Long productId) throws NotFoundException {
        return foodRepositoryRepository.findById(productId).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public Long save(Food food) {
        Food saveFood = foodRepositoryRepository.save(food);
        return saveFood.getId();
    }
}
