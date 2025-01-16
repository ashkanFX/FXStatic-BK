package com.example.FXstatic.repository;

import com.example.FXstatic.models.CategoryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryPostRepo extends JpaRepository<CategoryPost, Integer> {
    @Query(value = "SELECT p.title FROM category_post cp JOIN post p ON cp.p_id = p.id JOIN category c ON cp.c_id = c.id WHERE cp.c_id = :cId", nativeQuery = true)
    List<String> findByPostByCategoryId(@Param("cId") int cId);

}
