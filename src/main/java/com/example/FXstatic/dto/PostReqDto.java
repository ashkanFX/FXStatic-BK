package com.example.FXstatic.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostReqDto {
    String title;
    String context;
    String description;
    List<Integer> categories;
}
