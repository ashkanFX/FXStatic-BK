package com.example.FXstatic.service.impl;

import com.example.FXstatic.models.Document;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.repository.DocumentRepository;
import com.example.FXstatic.repository.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentImpl {
    private final DocumentRepository documentRepository;
    private final PostRepo postRepository;


    public void save(MultipartFile file, Long postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("User not found"));
//        if (documentRepository.findByPost(post.getId()).isEmpty()) {
            Document document = new Document();

            document.setFileName(file.getOriginalFilename());
            document.setPost(post);
            document.setContent(file.getBytes());

            documentRepository.save(document);
//        }
    }

    public List<Document> find() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(Long documentId) {
        return documentRepository.findById(documentId);
    }

    public List<Document> findByPostId(Long id) {
        return documentRepository.findByPost(id);
    }
}
