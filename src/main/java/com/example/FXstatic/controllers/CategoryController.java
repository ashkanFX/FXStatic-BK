package com.example.FXstatic.controllers;

import com.example.FXstatic.models.Category;
import com.example.FXstatic.service.impl.CategoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {


    final private CategoryImpl categoryImpl;

    @PostMapping()
    public void creatPost(@RequestBody Category category) {
        categoryImpl.save(category);
    }


    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        categoryImpl.delete(id);
    }

    @GetMapping("public/get/all")
    public List<Category> getAllPost() {
        return categoryImpl.find();
    }

}
