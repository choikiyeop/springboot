package com.example.springboot.domain.service;

import com.example.springboot.domain.dao.TodoRepository;
import com.example.springboot.domain.dto.TodoDto;
import com.example.springboot.domain.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> todolist() {
        return todoRepository.findAll();
    }

    @Transactional
    public void create(String content) {
        todoRepository.save(Todo.of(content));
    }

    public TodoDto read(Long id) {
        return TodoDto.from(todoRepository.findById(id).orElseThrow());
    }

    @Transactional
    public void update(Long id, String content) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setContent(content);
        todoRepository.save(todo);
    }

    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
