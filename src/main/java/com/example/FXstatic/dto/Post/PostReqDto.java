package com.example.FXstatic.dto.Post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PostReqDto {
    @NotNull(message = "Title cannot be null")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;


    @NotNull(message = "Context cannot be null")
    @Size(max = 200, message = "Context cannot exceed 200 characters")
    private String context;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Category ID cannot be null")
    private List<Integer> categories;

    private List<Long> file;
}
