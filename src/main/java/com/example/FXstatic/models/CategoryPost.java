package com.example.FXstatic.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category_post")
public class CategoryPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "p_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "c_id", referencedColumnName = "id", nullable = false)
    private Category category;

}
