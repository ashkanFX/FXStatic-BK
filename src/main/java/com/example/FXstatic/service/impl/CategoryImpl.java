package com.example.FXstatic.service.impl;

import com.example.FXstatic.dto.DatabaseDto;
import com.example.FXstatic.models.Category;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.repository.CategoryPostRepo;
import com.example.FXstatic.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    public void delete(int id) {
        categoryRepo.deleteById(id);
    }
    public List<DatabaseDto> getPostOfCategory(List<Integer> cIds) {
        List<Object[]> results = categoryPostRepo.findByPostByCategoryId(cIds);
        return results.stream()
                .map(row -> new DatabaseDto(
                        (Long) row[0],   // ID
                        (String) row[1], // Title
                        (String) row[2]  // Description
                ))
                .collect(Collectors.toList());

    }


}
