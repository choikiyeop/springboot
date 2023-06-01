package com.example.springboot.domain.dao;

import com.example.springboot.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findById(Long id);

    @Modifying
    @Query("update Todo t set t.views = :views where t.id = :todoId")
    void saveViewsFromRedis(Long todoId, Long views);
}
