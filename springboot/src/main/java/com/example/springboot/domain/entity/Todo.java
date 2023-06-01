package com.example.springboot.domain.entity;

import com.example.springboot.domain.dto.TodoDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Getter
@RequiredArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "content")
    private String content;

    private long views = 0;

    @CreationTimestamp
    private Timestamp createdAt;

    private Todo(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static Todo of(String content) {
        return new Todo(null, content);
    }

}
