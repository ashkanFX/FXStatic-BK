package com.example.FXstatic.dto;

import lombok.Data;

@Data
public class DatabaseDto {
    long id;
    String title;
    String description;
    public DatabaseDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
