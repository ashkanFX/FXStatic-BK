package com.example.FXstatic.repository;

import com.example.FXstatic.models.CategoryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryPostRepo extends JpaRepository<CategoryPost, Integer> {
    @Query(value = "SELECT distinct p.id, p.title, p.description " +
            "FROM category_post cp " +
            "JOIN post p ON cp.p_id = p.id " +
            "WHERE cp.c_id IN  (:cIds)", nativeQuery = true)
    List<Object[]> findByPostByCategoryId(@Param("cIds") List<Integer> cIds);

    @Query(value = "SELECT c.id, c.name  " +
            "FROM category_post cp " +
            "JOIN category c ON cp.c_id = c.id " +
            "WHERE cp.p_id  = (:pId)", nativeQuery = true)
    List<Object[]> findCategoryByPostId(@Param("pId") long pIds);
}
