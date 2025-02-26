package com.example.FXstatic.controllers;

import com.example.FXstatic.Exception.ResourceNotFoundException;
import com.example.FXstatic.dto.CategoryIdDto;
import com.example.FXstatic.dto.DatabaseDto;
import com.example.FXstatic.models.Category;
import com.example.FXstatic.service.impl.CategoryImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    final private CategoryImpl categoryImpl;

    @PostMapping()
    public ResponseEntity<String> creatPost(@Valid @RequestBody Category category, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            throw  new ResourceNotFoundException(errorMessage);
        }
        categoryImpl.save(category);
        return new ResponseEntity<>("Resource created successfully", HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")

    public void deletePost(@PathVariable int id) {

        categoryImpl.delete(id);
    }

    @GetMapping("public/get/all")
    public List<Category> getAllPost() {
        return categoryImpl.find();
    }

    @GetMapping("get/{id}")
    public Category getAllPost(@PathVariable int id) {
        return categoryImpl.findById(id).orElseThrow();
    }

    @PostMapping("public/get/post")
    public List<DatabaseDto> getPostOfCategory(@RequestBody CategoryIdDto categoryIdDto) {
        return categoryImpl.getPostOfCategory(categoryIdDto);
    }

}
