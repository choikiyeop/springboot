package com.example.springboot.domain.controller;

import com.example.springboot.domain.dto.TodoDto;
import com.example.springboot.domain.dto.request.TodoCreateRequest;
import com.example.springboot.domain.dto.request.TodoUpdateRequest;
import com.example.springboot.domain.entity.Todo;
import com.example.springboot.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/list")
    public List<TodoDto> list() {
        return todoService.todolist().stream().map(TodoDto::from).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void create(@RequestBody TodoCreateRequest request) {
        todoService.create(request.content());
    }

    @GetMapping("/{id}")
    public TodoDto read(@PathVariable("id") Long id) {
        return todoService.read(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody TodoUpdateRequest request) {
        todoService.update(id, request.content());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        todoService.delete(id);
    }
}
