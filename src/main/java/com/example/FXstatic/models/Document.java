package com.example.FXstatic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

@Data
@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(length = 90000)
    @Lob
    private byte[] Content;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @JsonIgnore
    private Post post;
    @Column
    private String fileName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return Objects.equals(getId(), document.getId()) && Arrays.equals(getContent(), document.getContent()) && Objects.equals(getPost(), document.getPost()) && Objects.equals(getFileName(), document.getFileName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getPost(), getFileName());
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", Content=" + Arrays.toString(Content) + ", post=" + post + ", fileName='" + fileName + '\'' + '}';
    }
}
