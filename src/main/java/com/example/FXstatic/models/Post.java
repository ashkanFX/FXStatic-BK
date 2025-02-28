package com.example.FXstatic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Enables Builder Pattern for easy object creation
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Title cannot be null")
    @Length(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotNull(message = "Context cannot be null")
    @Length(max = 200, message = "Context cannot exceed 200 characters")
    private String context;

    @Lob
    @NotNull(message = "Description cannot be null")
    private String description;


    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;


    private int countOfView;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> document;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
