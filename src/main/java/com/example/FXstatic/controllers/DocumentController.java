package com.example.FXstatic.controllers;

import com.example.FXstatic.models.Document;
import com.example.FXstatic.models.Post;
import com.example.FXstatic.repository.PostRepo;
import com.example.FXstatic.service.impl.DocumentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentImpl documentService;
    private final PostRepo postRepository;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("postId") Long postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("User not found"));
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setPost(post);
        document.setContent(file.getBytes());
        documentService.save(document);
        return "oke bod";
    }

    @PostMapping("/get")
    public Optional<Document> handelGetFile(@RequestParam("id") Long id) {
        return documentService.findById(id);
    }
}
