package com.example.springboot.domain.dto;

import com.example.springboot.domain.entity.Todo;


public record TodoDto(Long id, String content, long views) {

    public static TodoDto of(Long id, String content, long views) {
        return new TodoDto(id, content, views);
    }

    public static TodoDto from(Todo todo) {
        return new TodoDto(todo.getId(), todo.getContent(), todo.getViews());
    }
}
