package com.syncra.supermarket.Service;



import org.springframework.stereotype.Service;

import com.syncra.supermarket.Entity.CategoryEntity;
import com.syncra.supermarket.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }
}