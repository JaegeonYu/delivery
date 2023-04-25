package com.example.bio.service;

import com.example.bio.domain.Food;
import com.example.bio.domain.dto.FoodChangeRequest;
import com.example.bio.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Service
@Transactional
public class FoodService {
    private final FoodRepository foodRepositoryRepository;

    @Autowired
    public FoodService(FoodRepository productRepository) {
        this.foodRepositoryRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Food findById(Long foodId) throws NotFoundException {
        return foodRepositoryRepository.findById(foodId)
                .orElseThrow(NotFoundException::new);
    }

    public Long save(Food food) {
        Food saveFood = foodRepositoryRepository.save(food);
        return saveFood.getId();
    }

    @Transactional(readOnly = true)
    public List<Food> findAll() {
        return foodRepositoryRepository.findAll();
    }

    public Long delete(Long foodId) throws NotFoundException {
        Food findFood = foodRepositoryRepository.findById(foodId).
                orElseThrow(NotFoundException::new);

        foodRepositoryRepository.deleteById(foodId);
        return foodId;
    }

    public Long patch(Long foodId, FoodChangeRequest foodChangeRequest) throws NotFoundException {
        Food findFood = foodRepositoryRepository.findById(foodId)
                .orElseThrow(NotFoundException::new);

        findFood.patch(foodChangeRequest); // dirty check
        return foodId;
    }
}
