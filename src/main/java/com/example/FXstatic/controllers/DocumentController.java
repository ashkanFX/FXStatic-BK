package com.example.FXstatic.controllers;

import com.example.FXstatic.models.Document;
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

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("postId") Long postId) throws IOException {
        try {
            documentService.save(file, postId);
            return "oke bod";
        } catch (Exception e) {
            throw new IOException();
        }
    }

    @PostMapping("/get")
    public Optional<Document> handelGetFile(@RequestParam("id") Long id) {
        return documentService.findById(id);
    }
}
