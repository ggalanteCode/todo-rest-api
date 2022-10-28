package com.soprasteria.todorestapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.todorestapi.entity.Todo;
import com.soprasteria.todorestapi.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/alltodos")
	public ResponseEntity<List<Todo>> allTodos() {
		return new ResponseEntity<List<Todo>>(todoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/todobyid/{todoId}")
	public ResponseEntity<Object> todoById(@PathVariable(value = "todoId") Integer todoId) {
		Optional<Todo> findById = todoService.findTodoById(todoId);
		if(findById.isPresent()) {
			return new ResponseEntity<Object>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Todo not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/inserttodo")
	public ResponseEntity<Todo> insertTodo(@RequestBody Todo todo) {
		return new ResponseEntity<Todo>(todoService.createTodo(todo),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletetodo/{todoId}")
	public ResponseEntity<Todo> deleteTodo(@PathVariable(value = "todoId") Integer todoId) {
		todoService.deleteTodoById(todoId);
		return new ResponseEntity<Todo>(HttpStatus.OK);
	}
	
	@PutMapping("/updatetodo")
	public ResponseEntity<Object> updateTodo(@RequestBody Todo todo) {
		Optional<Todo> findById = todoService.findTodoById(todo.getId());
		if(findById.isPresent()) {
			return new ResponseEntity<Object>(todoService.createTodo(findById.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Todo not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/togglecompleted/{todoId}")
	public ResponseEntity<Object> toggleCompleted(@PathVariable(value = "todoId") Integer todoId) {
		Optional<Todo> findById = todoService.findTodoById(todoId);
		if(findById.isPresent()) {
			return new ResponseEntity<Object>(todoService.updateCompleted(findById.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Todo not found", HttpStatus.NOT_FOUND);
		}
	}

}
