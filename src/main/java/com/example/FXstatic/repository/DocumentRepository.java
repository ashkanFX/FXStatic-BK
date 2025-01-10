package com.example.FXstatic.repository;//package com.example.ptmedia.repository;

import com.example.FXstatic.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT * FROM Document  WHERE  Document.Post_id = id  ", nativeQuery = true)
    List<Document> findByPost(Long id);
}
