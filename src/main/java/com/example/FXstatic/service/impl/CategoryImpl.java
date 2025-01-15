package com.example.FXstatic.service.impl;

import com.example.FXstatic.models.Category;
import com.example.FXstatic.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryImpl {
    private final CategoryRepo categoryRepo;


    public void save(Category category) {
        categoryRepo.save(category);
    }


    public List<Category> find() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findById(int documentId) {
        return categoryRepo.findById(documentId);
    }

    public void delete(int id) {
        categoryRepo.deleteById(id);
    }

}
