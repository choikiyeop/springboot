package com.example.springboot.domain.dto;

import com.example.springboot.domain.entity.Todo;


public record TodoDto(Long id, String content) {

    public static TodoDto of(Long id, String content) {
        return new TodoDto(id, content);
    }

    public static TodoDto from(Todo todo) {
        return new TodoDto(todo.getId(), todo.getContent());
    }
}
