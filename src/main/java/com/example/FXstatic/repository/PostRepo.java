package com.example.FXstatic.repository;

import com.example.FXstatic.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
 @Query(value = "SELECT * FROM Post ORDER BY id DESC LIMIT 4", nativeQuery = true)
 List<Post> getLatest();

// @Query(value = "SELECT p.id FROM post p join document d on  p.id = d.id ", nativeQuery = true)
// List<Post> findPostIds();

}
