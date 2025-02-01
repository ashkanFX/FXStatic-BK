package com.example.FXstatic.dto;

import lombok.Data;

@Data
public class CategoryOfPostDto {
    private int id;

    private String name;

    public CategoryOfPostDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
