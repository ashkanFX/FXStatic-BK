package com.example.FXstatic.service.impl;

import com.example.FXstatic.models.Category;
import com.example.FXstatic.models.CategoryPost;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.repository.CategoryPostRepo;
import com.example.FXstatic.repository.CategoryRepo;
import com.example.FXstatic.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryPostImpl {
    @Autowired
    private CategoryPostRepo categoryPostRepository;

    @Autowired
    public PostRepo postRepo;
    @Autowired
    public CategoryRepo categoryRepo;

    public void getAllCategoryPosts() {
        List<CategoryPost> categoryPosts = categoryPostRepository.findAll();

        for (CategoryPost categoryPost : categoryPosts) {
            System.out.println("CategoryPost ID: " + categoryPost.getId());
            System.out.println("Post: " + categoryPost.getPost().getTitle());
            System.out.println("Category: " + categoryPost.getCategory().getName());
        }
    }

    public void add(long postId, int categoryId) {
        CategoryPost categoryPost = new CategoryPost();
        Post post = postRepo.findById(postId).orElse(null);
        categoryPost.setPost(post);
        Category category = categoryRepo.findById(categoryId).orElse(null);
        categoryPost.setPost(post);
        categoryPost.setCategory(category);
        categoryPostRepository.save(categoryPost);
//        System.out.println(categoryPostRepository.findByPostByCategoryId(3));
    }
}
