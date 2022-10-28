package com.soprasteria.todorestapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.todorestapi.entity.Todo;
import com.soprasteria.todorestapi.repo.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}
	
	public Optional<Todo> findTodoById(Integer id) {
		return todoRepository.findById(id);
	}
	
	@Transactional
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	@Transactional
	public void deleteTodoById(Integer id) {
		todoRepository.deleteById(id);
	}
	
	@Transactional
	public Todo updateTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	@Transactional
	public Todo updateCompleted(Todo todo) {
		if(todo.getCompletato().equals(true)) {
			todo.setCompletato(false);
		} else {
			todo.setCompletato(true);
		}
		return todoRepository.save(todo);
	}

}
