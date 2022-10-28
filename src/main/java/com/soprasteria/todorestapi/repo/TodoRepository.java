package com.soprasteria.todorestapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.todorestapi.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
