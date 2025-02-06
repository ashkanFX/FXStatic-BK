package com.example.FXstatic.dto;

import com.example.FXstatic.models.Document;
import com.example.FXstatic.models.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResDto {

    private long id;

    private String title;

    private String context;

    private String description;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private User user;

    private List<Document> document;

    private List<CategoryOfPostDto> categories;
}
