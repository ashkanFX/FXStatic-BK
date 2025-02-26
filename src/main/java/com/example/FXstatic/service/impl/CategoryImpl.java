package com.example.FXstatic.service.impl;

import com.example.FXstatic.dto.CategoryIdDto;
import com.example.FXstatic.dto.DatabaseDto;
import com.example.FXstatic.models.Category;
import com.example.FXstatic.repository.CategoryPostRepo;
import com.example.FXstatic.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryImpl {
    private final CategoryRepo categoryRepo;
    private final CategoryPostRepo categoryPostRepo;

    public void save(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> find() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findById(int documentId) {
        return categoryRepo.findById(documentId);
    }

    @Transactional
    public void delete(int id) {
        categoryRepo.deleteById(id);
    }

    public List<DatabaseDto> getPostOfCategory(CategoryIdDto categoryIdDto) {
        List<Object[]> results = categoryPostRepo.findByPostByCategoryId(categoryIdDto.getId());
        return results.stream().map(row -> new DatabaseDto((Long) row[0], (String) row[1], (String) row[2])).collect(Collectors.toList());

    }

}
