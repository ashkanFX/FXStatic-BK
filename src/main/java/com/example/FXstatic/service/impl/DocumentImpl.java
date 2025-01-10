package com.example.FXstatic.service.impl;

import com.example.FXstatic.models.Document;
import com.example.FXstatic.repository.DocumentRepository;
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


    public void saveFile(MultipartFile multipartFile) throws IOException {
        Document fileEntity = new Document();
        fileEntity.setContent(multipartFile.getBytes());
        documentRepository.save(fileEntity);
    }

    public void save(Document document) {
        documentRepository.save(document);

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
