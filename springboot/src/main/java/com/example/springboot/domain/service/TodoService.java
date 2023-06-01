package com.example.springboot.domain.service;

import com.example.springboot.domain.dao.TodoRepository;
import com.example.springboot.domain.dto.TodoDto;
import com.example.springboot.domain.entity.Todo;
import com.example.springboot.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final RedisUtil redisUtil;

    @Transactional
    @Scheduled(cron = "0 0/3 * * * ?")
    public void applyViewsToRDB() {
        Set<String> viewsKeys = redisUtil.getKeysByPattern("todoViews*");
        Iterator<String> it = viewsKeys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Long todoId = Long.parseLong(key.split("::")[1]);
            Long views = Long.parseLong(redisUtil.get(key));
            todoRepository.saveViewsFromRedis(todoId, views);
            redisUtil.delete(key);
            redisUtil.delete("todoViews::"+todoId);
        }
    }

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

    public void plusViewsToRedis(Long todoId) {
        String key = "todoViews::"+todoId;
        if (redisUtil.get(key) == null) {
            redisUtil.set(key, String.valueOf(todoRepository.findById(todoId).get().getViews()), 3);
        } else {
            redisUtil.increment(key);
        }
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
