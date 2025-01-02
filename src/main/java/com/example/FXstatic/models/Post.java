package com.example.FXstatic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @Length(max = 200)
    @NotNull
    private String context;

    @Lob
    @NotNull
    private String description;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
